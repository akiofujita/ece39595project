package game;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class KeyStrokePrinter implements InputObserver, Runnable {

    private static int DEBUG = 1;
    private static String CLASSID = "KeyStrokePrinter";
    private static Queue<Character> inputQueue = null;
    private ObjectDisplayGrid displayGrid;
    private Dungeon dungeon;

    public KeyStrokePrinter(ObjectDisplayGrid grid, Dungeon _dungeon) {
        inputQueue = new ConcurrentLinkedQueue<>();
        displayGrid = grid;
        dungeon = _dungeon;
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
                if (ch == 'X') {
                    System.out.println("got an X, ending input checking");
                    return false;
                } else {
                    System.out.println("character " + ch + " entered on the keyboard");
                }

                switch (ch) {
                    case 'h':
                        dungeon.move(displayGrid, -1, 0);
                        break;

                    case 'j':
                        dungeon.move(displayGrid, 0, -1);
                        break;

                    case 'k':
                        dungeon.move(displayGrid, 0, 1);
                        break;

                    case 'l':
                        dungeon.move(displayGrid, 1, 0);
                        break;

                    case 'd':
                        dungeon.drop(displayGrid);
                        break;
                        
                    case 'p':
                        dungeon.pick(displayGrid);
                        break;

                    // case 'a':
                    //     dungeon.print(displayGrid);

                    case 'i':
                        dungeon.displayPack(displayGrid);
                        break;
                        
                    default:
                        break;
                }
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
