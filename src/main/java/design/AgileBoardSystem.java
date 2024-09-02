package design;

import java.util.Date;
import java.util.List;
import lombok.Data;

enum UserAuthority {
  ADMIN, PROJECT_MEMBER
}

enum JiraPriority {
  P0, P1, P2, P3
}

@Data
class User {
  private String userId;
  private String email;
  private UserAuthority authority;
}

@Data
class Sprint {
  private String id;
  private String name;
  private Date startDate;
  private Date endDate;
  private boolean finished;
}

@Data
class Tag {
  private String key;
  private String value;
}

@Data
class Comment {
  private User user;
  private String comment;
}

@Data
class Jira {
  protected String id;
  protected String subject;
  protected List<Comment> comments;
}

class Field<T> {
  private T value;
  private boolean visible;
}

abstract class FieldTemplate {
  public static Field<JiraPriority> PRIORITY_FIELD;
}

@Data
class Project {
  private String projectName;
  private List<User> projectMembers;
}

public class AgileBoardSystem {
}
