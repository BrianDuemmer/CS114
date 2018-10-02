
public class Hand extends CardContainer
{
  private String playerName;
  
  public Hand(String playerName, int numCards) {
    this.playerName = playerName;
    cards = new Card[numCards];
    
    for (int i = 0; i < numCards; i++)
      cards[i] = null;
  }
  
  /**
   * Adds a {@link Card} to the deck. It will try to replace the first null
   * element found in cards[]
   * 
   * @return true if there was a null slot in cards[] and the card got added,
   *         false if the Hand is full
   */
  @Override
  public boolean insertCard(Card c) {
    for (int i = 0; i < cards.length; i++)
      if (cards[i] == null) {
        cards[i] = c;
        return true;
      }
    return false;
  }
  
  @Override
  public boolean hasSpace() {
    return spaceRemaining() > 0;
  }
  
  /**
   * Counts the number of null slots in {@code cards}, into 
   * which cards can be placed
   */
  @Override
  public int spaceRemaining() {
    int numNull = 0;
    for(Card c : cards)
      if(c == null)
        numNull++;
    return numNull;
  }

  public String getPlayerName() {
    return playerName;
  }
  
}
