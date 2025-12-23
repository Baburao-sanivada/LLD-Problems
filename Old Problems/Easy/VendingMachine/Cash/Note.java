package VendingMachine.Cash;

public class Note extends Cash{
  public Note(NoteType noteType){
    super(noteType.rawValue());
  }
}