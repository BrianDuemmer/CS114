/**
 * Represents a container and manager of {@link Card}s.
 * 
 * @author Duemmer
 *
 */
public abstract class CardContainer
{
  protected Card[] cards;
  
  /**
   * Attempts to add a card to the container
   * 
   * @param c
   *          the card to add
   * @return true iff the card was added successfully, false otherwise
   */
  public abstract boolean insertCard(Card c);
  
  /**
   * Indicates whether this container can accept this card
   * 
   * @param c
   *          The {@link Card}
   * @return true if a call to {@link CardContainer#insertCard(Card)
   *         insertCard(c)} would succeed
   */
  public abstract boolean canAcceptCard(Card c);
  
  /**
   * Indicates whether this container has room for more cards
   * 
   * @return true iff another card can be added
   */
  public boolean hasSpace() {
    for (Card c : cards)
      if (c == null) return true;
    return false;
  }
  
  public Card[] getCards() {
    return cards;
  }
  
  /**
   * Gets the number of non-null cards in the container
   * 
   * @return the number of non-null cards in cards
   */
  public int getCardCount() {
    int i = 0;
    for (Card c : cards)
      if (c != null) i++;
    return i;
  }
  
  /**
   * Checks if a (non-null) card exists in cards
   * 
   * @param cardChk
   *          the card to check for
   * @return true iff cardChk is not null and an equivalent card is found in
   *         cards
   */
  public boolean containsCard(Card cardChk) {
    if (cardChk == null) return false;
    for (Card c : cards)
      if (cardChk.equals(c)) return true;
    return false;
  }
  
  /**
   * Debugging method to view the contents of cards
   * 
   * @param printNulls
   *          whether or not to skip null entries in the list
   * @return A string representing the contents of cards
   */
  public String printCards(boolean printNulls) {
    StringBuilder sb = new StringBuilder();
    for (Card c : cards)
      if (printNulls || c != null) {
        sb.append(c);
        sb.append(" ");
      }
    return sb.toString();
  }
  
  /**
   * If this container contains c, and c is a valid card, it will be removed
   * 
   * @param c
   *          the {@link Card} to remove
   * @return true if c is {@link Card#valid(Card) valid}, and is present in
   *         cards
   */
  protected boolean removeCard(Card c) {
    if (Card.valid(c)) for (int i = 0; i < cards.length; i++)
      if (c.equals(cards[i])) {
        cards[i] = null;
        return true;
      }
    return false;
  }
  
  /**
   * Transfers a card from this {@link CardContainer} to another
   * 
   * @param other
   *          the CardContainer to transfer to
   * @param c
   *          The {@link Card} to transfer to
   * @return true if the transfer completed successfully
   */
  public boolean passCard(CardContainer other, Card c) {
    boolean success = false;
    // once we verify that the other is ready, attempt to remove the card.
    // if successful, the transfer can take place
    if (other != null && other.canAcceptCard(c) && removeCard(c)) {
      // This should never fail in theory, as canAcceptCard would have returned
      // false if there was an issue. Just to be safe, be ready to roll back
      if (!other.insertCard(c)) {
        success = false;
        insertCard(c);
      }
      success = true;
    }
    return success;
  }
}
