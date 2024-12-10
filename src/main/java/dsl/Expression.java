package dsl;

import static dsl.Operator.*;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.NonNull;
import org.springframework.util.Assert;


@Data
public class Expression extends HashMap<Operator, Object[]> {

  private static final Map<Operator, List<String>> ALLOWED_CLASSES_FOR_OPERATORS = new HashMap<>();
  private String fieldType;
  private String listContentFieldType;

  static {
    List<String> supportingEquals = asList(String.class.getTypeName(),
        Integer.class.getTypeName(), Float.class.getTypeName(), Date.class.getTypeName(), Float.class.getTypeName());
    ALLOWED_CLASSES_FOR_OPERATORS.put($eq, supportingEquals);
    ALLOWED_CLASSES_FOR_OPERATORS.put($ne, supportingEquals);

    List<String> supportingGtLt = asList(Integer.class.getTypeName(),
        Float.class.getTypeName(), Date.class.getTypeName(), Float.class.getTypeName());
    ALLOWED_CLASSES_FOR_OPERATORS.put($gt, supportingGtLt);
    ALLOWED_CLASSES_FOR_OPERATORS.put($ge, supportingGtLt);
    ALLOWED_CLASSES_FOR_OPERATORS.put($lt, supportingGtLt);
    ALLOWED_CLASSES_FOR_OPERATORS.put($le, supportingGtLt);
  }

  private Expression() {
  }

  @Override
  public Object[] put(@NonNull Operator operator, @NonNull Object[] value) {
    validate(this.fieldType, operator, value);
    return super.put(operator, value);
  }

  private void validate(String fieldType, Operator operator, Object[] values) {
    switch (operator) {
      case or, and -> {
        Assert.isTrue(values.length > 1, "and/or supports 2 or more expressions");
        for (Object v : values) {
          Assert.isTrue((v instanceof Expression), "and/or expressions should only have Expression objects");
        }
      }
      default -> {
        List<String> allowedTypes = ALLOWED_CLASSES_FOR_OPERATORS.getOrDefault(operator, emptyList());
        Assert.notEmpty(allowedTypes, String.format("Operator: %s not supported", operator));
        Assert.isTrue(allowedTypes.contains(fieldType),
            String.format("Operator: %s, is not supported for fieldType: %s", operator, fieldType));
      }
    }
  }

  public static Expression of(@NonNull Operator operator, @NonNull String fieldName, @NonNull Object fieldValue) {
    Expression expression = new Expression();
    expression.setFieldType(fieldValue.getClass().getTypeName());
    expression.put(operator, new Object[] {fieldName, fieldValue});
    return expression;
  }

  public Expression or(@NonNull Expression... expressions) {
    Expression orExpr = nestedExpression();
    List<Expression> expressionList = Arrays.stream(expressions).collect(Collectors.toList());
    expressionList.add(this);
    orExpr.put(or, expressionList.toArray());
    return orExpr;
  }

  public Expression and(@NonNull Expression... expressions) {
    Expression orExpr = nestedExpression();
    List<Expression> expressionList = Arrays.stream(expressions).collect(Collectors.toList());
    expressionList.add(this);
    orExpr.put(and, expressionList.toArray());
    return orExpr;
  }

  private Expression nestedExpression(Expression... expressions) {
    Expression nestedExpression = new Expression();
    nestedExpression.setFieldType(List.class.getTypeName());
    nestedExpression.setListContentFieldType(Expression.class.getTypeName());
    return nestedExpression;
  }
}
