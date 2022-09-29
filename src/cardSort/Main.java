//// cardSort
// program for creating, editing, shuffling, and sorting a deck of cards using Fisher-Yates, bubble, and insertion for CSCI 211
// last edited Sep. 14, 2022 by S. Gutierrez

// includes project package
package cardSort;

// imports
import java.util.LinkedHashMap;
import java.util.Random;

//// Main
// contains methods for operating on a deck of Card[] + driver code
public class Main {

    //// buildDeck()
    // creates an array of 52 Card objects
    public static Card[] buildDeck(LinkedHashMap<String, Integer> values, LinkedHashMap<String, Integer> suits) {

        // creates an array of 52 Card objects
        Card[] deck = new Card[52];

        // tracks value with counter
        int deckIdx = 0;

        // iterates through dictionary of suits keys
        for (String suit : suits.keySet()) {

            // iterates through dictionary of values keys
            for (String value : values.keySet()) {

                // assigns array position at counter index to new Card object
                deck[deckIdx] = new Card(value, suit, values.get(value), suits.get(suit), deckIdx);

                // increases counter
                deckIdx++;

            } // ends values loop

        } // ends suits loop

        // returns created deck of Card[]
        return deck;

    } // ends buildDeck() method

    //// shuffleDeck()
    // uses Fisher-Yates to randomly shuffle a Card[] deck array
    public static Card[] shuffleDeck(Card[] deck) {

        // instantiates Random object for operation
        Random rand = new Random();

        // iterates backwards from end of deck
        for (int cardIdx = deck.length - 1; cardIdx > 0; cardIdx--) {

            // generates random number to size of deck for indexing
            int ranIdx = rand.nextInt(cardIdx + 1);
            // holds current card
            Card hold = deck[cardIdx];
            // assigns random card to current card
            deck[cardIdx] = deck[ranIdx];
            // assigns hold (current card) to random card
            deck[ranIdx] = hold;

        } // ends for loop

        // returns shuffled deck
        return deck;

    } // ends shuffleDeck() method

    //// printDeck()
    // prints each card in a Card[] array/deck
    public static void printDeck(Card[] deck) {

        // iterates for each Card object in Card[] array
        for (Card card : deck) {

            // invokes toString() for Card object and prints formatted result
            System.out.println(card.toString());

        } // ends for each loop

        // breaks for readability
        System.out.println("\n\n\n");

    } // ends printDeck() method

    //// sortDeckValue()
    // sorts strictly according to card value via bubble
    // does not consider suits
    public static Card[] sortDeckValue(Card[] deck, LinkedHashMap<String, Integer> values) {

        // loops primary index within constraints of deck size
        for (int pri = 0; pri < deck.length - 1; pri++) {

            // loops secondary index within constraints of deck size
            for (int sec = 0; sec < deck.length - pri - 1; sec++) {

                // grabs linked hash map Integer value corresponding with deck String value
                int v1 = values.get(deck[sec].getValue());
                int v2 = values.get(deck[sec + 1].getValue());

                // compares if card value is bigger than following card value
                if (v1 > v2) {

                    // holds temporary value to be switched
                    Card hold = deck[sec];
                    // reassigns from x + 1 to x
                    deck[sec] = deck[sec + 1];
                    // gets x from hold and reassigns to x + 1
                    deck[sec + 1] = hold;

                } // ends card value comparison
            } // ends secondary index loop
        } // ends primary index loop

        // returns a value sorted deck
        return deck;

    } // ends sortDeckValue() method

    //// sortDeckValueSuit()
    // sorts according to suit and value via insertion
    public static Card[] sortDeckValueSuit(Card[] deck) {

        // loops starting from second position in deck
        // first position assumed ordered
        for (int sec = 1; sec < deck.length; sec++) {

            // holds temp value at iteration index
            Card hold = deck[sec];
            // holds value prior to hold
            int pri = sec - 1;

            // while prior value is in deck range and the prior card's rank in the deck is bigger than the current card's rank in the deck
            while (pri >= 0 && deck[pri].getDeckRank() > hold.getDeckRank()) {

                // moves prior value to sec, following position
                deck[pri + 1] = deck[pri];
                // moves prior position down the line further
                pri--;

            } // ends while loop

            // switches larger current compared card with smaller held card
            deck[pri + 1] = hold;

        } // ends for loop

        // returns deck sorted by suit and value
        return deck;

    } // ends sortDeckValueSuit() method

    //// MAIN METHOD / DRIVER CODE ////
    public static void main(String[] args) {

        // inits raw values and suits for the deck
        String[] valuesRaw = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suitsRaw = {"C", "D", "H", "S"};

        // assigns raw values + suits to hashmaps, preps for string comparison //

        // inits linked hash map
        LinkedHashMap<String, Integer> values = new LinkedHashMap<>();
        // tracks counter for assigning int to value key
        int vCount = 0;
        // loops for each raw string value
        for (String value : valuesRaw) {
            // assigns to map
            values.put(value, vCount);
            // increases int value
            vCount++;
        } // ends for loop

        // inits linked hash map
        LinkedHashMap<String, Integer> suits = new LinkedHashMap<>();
        // tracks counter for assigning int to suit key
        int sCount = 0;
        // loops for each raw string suit
        for (String suit : suitsRaw) {
            // assigns to map
            suits.put(suit, sCount);
            // increases int value
            sCount++;
        } // ends for loop

        // inits, builds, returns deck of 52 Card obj array
        Card[] deckBuilt = buildDeck(values, suits);
        // prints built deck VALUE - SUIT
        System.out.println("BUILT DECK:");
        printDeck(deckBuilt);

        // shuffles the array
        Card[] deckShuffled = shuffleDeck(deckBuilt);
        // prints shuffled deck VALUE - SUIT
        System.out.println("SHUFFLED DECK:");
        printDeck(deckShuffled);

        // sorts value-based, (2 thru A)
        Card[] deckValueSorted = sortDeckValue(deckShuffled, values);
        // prints value-based sorted deck
        System.out.println("VALUE SORTED DECK:");
        printDeck(deckValueSorted);

        // sorts suit/value-based, (C, D, H, S), (2 thru A)
        Card[] deckValueSuitSorted = sortDeckValueSuit(deckShuffled);
        // prints suit/value-based sorted deck
        System.out.println("VALUE-SUIT SORTED DECK:");
        printDeck(deckValueSuitSorted);

    } // ends main method

} // ends Main class
