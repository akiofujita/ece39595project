package game;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class KeyStrokePrinter implements InputObserver, Runnable {

    private static int DEBUG = 1;
    private static String CLASSID = "KeyStrokePrinter";
    private static Queue<Character> inputQueue = null;
    private ObjectDisplayGrid displayGrid;
    private Dungeon dungeon;
    private char prevKeyInput;
    private String cmdString = "hlkji?HcdpRTwE0123456789";

    public KeyStrokePrinter(ObjectDisplayGrid grid, Dungeon _dungeon) {
        inputQueue = new ConcurrentLinkedQueue<>();
        displayGrid = grid;
        dungeon = _dungeon;
        prevKeyInput = ' ';
    }

    @Override
    public void observerUpdate(char ch) {
        if (DEBUG > 0) {
            // System.out.println(CLASSID + ".observerUpdate receiving character " + ch);
        }
        inputQueue.add(ch);
    }

    private void rest() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private boolean processInput() {

        char ch;

        boolean processing = true;
        while (processing) {
            if (inputQueue.peek() == null) {
                processing = false;
            } else {
                ch = inputQueue.poll();
                if (DEBUG > 1) {
                    System.out.println(CLASSID + ".processInput peek is " + ch);
                }

                /* If d and then a number is pressed, then drop item */
                if (prevKeyInput == 'd' && Character.isDigit(ch)) {
                    dungeon.drop(displayGrid, Character.getNumericValue(ch));
                }
                /* If r and then a number is pressed, then drop item */
                else if (prevKeyInput == 'r' && Character.isDigit(ch)) {
                    dungeon.read(displayGrid, Character.getNumericValue(ch));
                }
                /* If H and then a number is pressed, then display command description */
                else if (prevKeyInput == 'H' && cmdString.contains(String.valueOf(ch))) {
                    dungeon.helpInfo(displayGrid, ch);
                }
                else if (prevKeyInput == 'w' && Character.isDigit(ch)) {
                    dungeon.wearArmor(displayGrid, Character.getNumericValue(ch));
                }
                else if (prevKeyInput == 'T' && Character.isDigit(ch)) {
                    dungeon.takeOutWeapon(displayGrid, Character.getNumericValue(ch));
                }
                /* If E and then a Y or y is pressed, then end game */
                else if (prevKeyInput == 'E' && (ch == 'y' || ch == 'Y')) {
                    System.out.println("Got an E <Y|y>, ending input checking");
                    dungeon.endGameDungeon(displayGrid, "Game Ended");
                    return false;
                }
                else {
                    System.out.println("Pressed " + ch);

                    /* Check key input */
                    switch (ch) {

                        /* Move left */
                        case 'h':
                            dungeon.move(displayGrid, -1, 0);
                            break;

                        /* Move down */
                        case 'j':                        
                            dungeon.move(displayGrid, 0, 1);
                            break;

                        /* Move up */
                        case 'k':
                            dungeon.move(displayGrid, 0, -1);
                            break;

                        /* Move right */
                        case 'l':
                            dungeon.move(displayGrid, 1, 0);
                            break;

                        /* Pick up item */
                        case 'p':
                            dungeon.pick(displayGrid);
                            break;

                        /* Show inventory */
                        case 'i':
                            dungeon.displayPack(displayGrid);
                            break;

                        case 'c':
                            dungeon.changeArmor(displayGrid);
                            break;

                        // /* Show stack at current location (debug) */
                        // case 'a':
                        //     dungeon.print(displayGrid);
                            
                        case '?':
                            dungeon.help(displayGrid);
                            break;
                        default:
                            break;
                    }
                }
                prevKeyInput = ch;
            }
        }
        return true;
    }

    @Override
    public void run() {
        displayGrid.registerInputObserver(this);
        boolean working = true;
        while (working) {
            rest();
            working = (processInput( ));
        }
    }
}
