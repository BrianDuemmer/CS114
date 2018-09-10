
public class Deck extends CardContainer
{
  private static final int SHUFFLE_PERMUTATION_ITERATIONS = 2;
  
  public Deck() {
    cards = new Card[52];
    for(int i=0; i<cards.length; i++)
      cards[i] = null;
    
    // iterate over the 4 suits, and for each create each number card
    int i=0;
    for(char suit : "dcsh".toCharArray())
      for(int num=1; num<=13; num++) {
        insertCard(new Card(suit, num));
        i++;
      }
  }
  
  
  public boolean deal(CardContainer dst, int count) {
    // check that this Deck can deal these cards, and that dst can recieve them
    if(dst == null || dst.spaceRemaining() < count || getCardCount() < count)
      return false;
      
    // At this point there should be no other failure conditions, so we write the cards to dst as soon as we read them
    for(int i=0; i<count; i++) { // operation number
      for(int j=0; j<cards.length; j++) { // card number being checked for validity
        if(cards[j] != null) {
          Card tmp = cards[j];
          if(!passCard(dst, tmp)) // if this triggers, it means an illegal state has been reached
            throw new IllegalStateException("Unexpexpected transfer failure in deck.deal()");
        }
      }
    }
  }
  
  
  public boolean deal(CardContainer dst) {
    return deal(dst, 1);
  }
  
  /**
  * Multi-iteration swap randomizer, based off the Knuth Shuffle.
  * Iterations of the algorithm are specified by SHUFFLE_PERMUTATION_ITERATIONS
  */
  public void shuffle() {
    Random r = new Random();
    for(int i=0; i<SHUFFLE_PERMUTATION_ITERATIONS; i++)
      for(int j=0; j<cards.length-1; j++) {
        int swapIdx = j + r.nextInt((cards.length-1)-j);
        Card tmp = cards[j];
        cards[j] = cards[swapIdx];
        cards[swapIdx] = tmp;
      }
  }
  

  @Override
  public boolean insertCard(Card c) {
    if(!hasSpace())
      return false;
    // get the index of the last non-null card, and assign this card to it
    int i = spaceremaining();
    cards[cards[(cards.length)-i]] = c;
    return true;
  }
  
  /**
   * Checks if there is space at the back of the cards array to accept another card
   * 
   * @return the number of trailing null elements 
   */
  @Override
  public int spaceRemaining() {
    int i = 0;
    while(cards[(cards.length-1)-i] == null)
      i++;
    return i;
  }
  
}
