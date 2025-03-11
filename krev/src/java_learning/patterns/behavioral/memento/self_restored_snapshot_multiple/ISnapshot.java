package java_learning.patterns.behavioral.memento.self_restored_snapshot_multiple;

//now it is NOT necessary for protection of snapshot's state,
//but it gives opportunity to have different classes of Snapshots
public interface ISnapshot {
    String getSnapshotName();
    void restore();
}
