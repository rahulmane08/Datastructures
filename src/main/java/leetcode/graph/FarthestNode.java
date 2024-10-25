package leetcode.graph;

public class FarthestNode {
  private final Integer fromVertex;
  private Integer farthestNode;
  private Integer depth;

  public FarthestNode(Integer fromVertex) {
    this.fromVertex = fromVertex;
    this.depth = -1;
  }

  public Integer getFromVertex() {
    return fromVertex;
  }

  public Integer getFarthestNode() {
    return farthestNode;
  }

  public void setFarthestNode(Integer farthestNode) {
    this.farthestNode = farthestNode;
  }

  public Integer getDepth() {
    return depth;
  }

  public void setDepth(Integer depth) {
    this.depth = depth;
  }
}
