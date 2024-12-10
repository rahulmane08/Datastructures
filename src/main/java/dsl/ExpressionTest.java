package dsl;

import static dsl.Operator.$eq;
import static dsl.Operator.$gt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ExpressionTest {
  public static void main(String[] args) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    Expression expression = Expression.of($eq, "poOrder", "abc");
    System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(expression));

    expression = expression.or(Expression.of($eq, "shipmentId", "xyz"));
    System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(expression));

    Expression second = Expression.of($gt, "totalProducts", 1);
    expression = second.and(expression);
    System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(expression));
  }
}
