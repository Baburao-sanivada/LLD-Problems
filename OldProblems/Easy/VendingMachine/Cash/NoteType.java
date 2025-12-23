package VendingMachine.Cash;

public enum NoteType{
  TEN(10),
  TWENTY(20),
  FIFTY(50),
  HUNDRED(100);


  private final int value;

  NoteType(int value){
    this.value = value;
  }

  public int rawValue(){
    return value;
  }
}