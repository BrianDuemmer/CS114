
public class TestMain
{
  
  public static void main(String[] args) {
    Deck deck = new Deck();
    System.out.println(deck.printCards(true));
    deck.shuffle();
    System.out.println(deck.printCards(true));
    
    Card c = new Card('h', 7);
    Hand h = new Hand("Throcky", 6);
    boolean success = deck.passCard(h, c);
    deck.deal(h, 5);
    System.out.println(deck.getCardCount());
    System.out.println(h.getCardCount());
    System.out.println(success);
  }
  
}
