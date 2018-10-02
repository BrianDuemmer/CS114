import java.util.Random;

public class Deck extends CardContainer
{
  private static final int SHUFFLE_PERMUTATION_ITERATIONS = 2;
  
  
  /**
   * Creates a new {@link Deck} filled with a standard deck of 52
   * cards. By default, they will not be randomized. An explicit call to
   * {@link #shuffle()} should be performed before use of the Deck
   * to ensure a random order of cards
   */
  public Deck() {
    cards = new Card[52];
    for(int i=0; i<cards.length; i++)
      cards[i] = null;
    
    // iterate over the 4 suits, and for each create each number card
    for(char suit : "dcsh".toCharArray())
      for(int num=1; num<=13; num++) {
        insertCard(new Card(suit, num));
      }
  }
  
  /**
   * Deals cards from this {@link Deck} to another {@link CardContainer}. This will check
   * that both this Deck and {@code dst} have enough cards and room, respectively, 
   * to perform the transfer. If those conditions are met, it will attempt
   * to pop the first {@code count} non-null cards off the Deck, null them, and attempt to add
   * them to {@code dst}. If this fails, which shouldn't be possible at this point, an 
   * {@link IllegalStateException} will be thrown.
   * @param dst The {@link CardContainer} to deal cards to
   * @param count the number of cards to deal
   * @return true on a successful transfer, false otherwise
   * @throws IllegalStateException if a transfer fails after preconditions were verified
   * to allow a transfer
   */
  public boolean deal(CardContainer dst, int count) {
    // check that this Deck can deal these cards, and that dst can recieve them
    if(dst == null || dst.spaceRemaining() < count || getCardCount() < count || count <= 0)
      return false;
      
    // At this point there should be no other failure conditions, so we write the cards to dst as soon as we read them
    for(int i=0; i<count; i++) { // operation number
      for(int j=0; j<cards.length; j++) { // card number being checked for validity
        if(cards[j] != null) {
          if(!passCard(dst, cards[j])) // if this triggers, it means an illegal state has been reached
            throw new IllegalStateException("Unexpexpected transfer failure in deck.deal()");
          break;
        }
      }
    }
    
    return true;
  }
  
  /**
   * Deals a single card to another {@link CardContainer}
   * @see #deal(CardContainer, int)
   * @param dst the other {@link CardContainer}
   * @return true on a successful transfer, false otherwise
   */
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
      for(int j=0; j<cards.length; j++) {
        int swapIdx = r.nextInt(cards.length-1);
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
    int i = spaceRemaining();
    cards[cards.length-i] = c;
    return true;
  }
  
  /**
   * Checks if there is space at the back of the cards array to accept another card
   * 
   * @return the number of trailing null elements 
   */
  @Override
  public int spaceRemaining() {
    int space = 0;
    for(int i=cards.length-1; i>=0; i--) {
      if(cards[i] != null) {
        break;
      } else
        space++;
    }
    
    return space;
  }
  
}
