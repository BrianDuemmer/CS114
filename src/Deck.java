
public class Deck extends CardContainer
{
  public Deck() {
    cards = new Card[52];
    
    // iterate over the 4 suits, and for each create each number card
    int i=0;
    for(char suit : "dcsh".toCharArray())
      for(int num=1; num<=13; num++) {
        cards[i] = new Card(suit, num);
        i++;
      }
  }

  @Override
  public boolean insertCard(Card c) {
    // get the index of the last non-null card
    int i;
    for(i=cards.length-1; i>=0; i--)
      if(cards[i] != null)
        break;
    
    // last card was non-null, so there's no space left
    if(i == cards.length-1)
      return false;
    cards[i+1] = c;
    return true;
  }

  @Override
  public boolean canAcceptCard(Card c) {
    // TODO Auto-generated method stub
    return false;
  }
  
}
