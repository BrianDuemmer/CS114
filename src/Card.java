
public class Card
{
  private char suit;
  private int num;
  
  public Card(char suit, int num) {
    this.suit = suit;
    this.num = num;
    
    // bounds check
    if (suit != 'd' && suit != 'c' && suit != 's' && suit != 'h')
      throw new IllegalArgumentException(
          String.format("'%c' is an invalid suit!", suit));
    
    if (num < 1 || num > 13) throw new IllegalArgumentException(
        String.format("%d is an invalid card number!!", num));
  }
  
  /**
   * Returns a String in the form "[card][suit]", e.g "As", "5d", etc.
   */
  @Override
  public String toString() {
    String val;
    switch (num) {
      case 1:
        val = "A";
        break;
      case 11:
        val = "J";
        break;
      case 12:
        val = "Q";
        break;
      case 13:
        val = "K";
        break;
      default:
        val = String.valueOf(num);
        break;
    }
    
    return String.format("%s%c", val, suit);
  }
  
  /**
   * Returns true iff obj is a non null {@link Card} with an identical suit and
   * number
   */
  @Override
  public boolean equals(Object obj) {
    if (obj != null) try {
      Card c = (Card) obj;
      return c.suit == suit && c.num == num;
    } catch (ClassCastException e) {
    }
    return false;
  }
  
  
  /**
   * Checks if a card is non-null, with valid suit and number.
   * @param c the card to check
   * @return true iff c is not null and 
   */
  public static boolean valid(Card c) {
    if (c != null)
      return c.num >= 1 && c.num <= 13 && "dcsh".indexOf(c.suit) >= 0;
    return false;
  }

  public char getSuit() {
    return suit;
  }

  public int getValue() {
    return num;
  }
}





