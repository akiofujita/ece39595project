package game;

import asciiPanel.AsciiPanel;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ObjectDisplayGrid extends JFrame implements KeyListener, InputSubject {

    private static final int DEBUG = 0;
    private static final String CLASSID = ".ObjectDisplayGrid";

    private static AsciiPanel terminal;
    private Stack<Displayable>[][] objectGrid = null;

    private List<InputObserver> inputObservers = null;

    private static int height;
    private static int width;

    public ObjectDisplayGrid(int _width, int _height) {
        width = _width;
        height = _height;

        terminal = new AsciiPanel(width, height);

        objectGrid = new Stack[width][height];

        initializeDisplay();

        super.add(terminal);
        super.setSize(width * 9, height * 16);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // super.repaint();
        // terminal.repaint( );
        super.setVisible(true);
        terminal.setVisible(true);
        this.addKeyListener(this);
        terminal.addKeyListener(this);
        inputObservers = new ArrayList<>();
        super.repaint();
    }

    @Override
    public void registerInputObserver(InputObserver observer) {
        if (DEBUG > 0) {
            System.out.println(CLASSID + ".registerInputObserver " + observer.toString());
        }
        inputObservers.add(observer);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (DEBUG > 0) {
            System.out.println(CLASSID + ".keyTyped entered" + e.toString());
        }
        KeyEvent keypress = (KeyEvent) e;
        notifyInputObservers(keypress.getKeyChar());
    }

    private void notifyInputObservers(char ch) {
        for (InputObserver observer : inputObservers) {
            observer.observerUpdate(ch);
            if (DEBUG > 0) {
                System.out.println(CLASSID + ".notifyInputObserver " + ch);
            }
        }
    }

    // we have to override, but we don't use this
    @Override
    public void keyPressed(KeyEvent even) {
    }

    // we have to override, but we don't use this
    @Override
    public void keyReleased(KeyEvent e) {
    }

    public final void initializeDisplay() {
        Displayable blank = new Displayable();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                objectGrid[i][j] = new Stack<Displayable>();
                objectGrid[i][j].push(blank);
            }
        }
        terminal.repaint();
    }

    public void fireUp() {
        if (this.requestFocusInWindow()) {
            System.out.println(CLASSID + ".ObjectDisplayGrid(...) requestFocusInWindow Succeeded");
        } else {
            System.out.println(CLASSID + ".ObjectDisplayGrid(...) requestFocusInWindow FAILED");
        }
    }

    public void addObjectToDisplay(Displayable object, int x, int y) {
        if ((0 <= x) && (x < objectGrid.length)) {
            if ((0 <= y) && (y < objectGrid[0].length)) {
                // System.out.println(object.getType() + ": " + x + ", " + y);
                objectGrid[x][y].push(object);
                writeToTerminal(x, y);
            }
        }
    }

    public void addObjectToDisplay(Displayable object, int x, int y, int index) {
        if ((0 <= x) && (x < objectGrid.length)) {
            if ((0 <= y) && (y < objectGrid[0].length)) {
                // System.out.println(object.getType() + ": " + x + ", " + y);
                if (0 <= index && index <= objectGrid[x][y].size()) {
                    objectGrid[x][y].add(index, object);
                    writeToTerminal(x, y);
                }
            }
        }
    }

    public Displayable removeObjectFromDisplay(int x, int y) {
        Displayable object = null;
        if ((0 <= x) && (x < objectGrid.length)) {
            if ((0 <= y) && (y < objectGrid[0].length)) {
                // System.out.println(object.getType() + ": " + x + ", " + y);
                if (objectGrid[x][y].peek().getType() != ' ') {
                    object = objectGrid[x][y].pop();
                    writeToTerminal(x, y);
                }
            }
        }
        return object;
    }

    public Displayable removeObjectFromDisplay(int x, int y, int index) {
        Displayable object = null;
        if ((0 <= x) && (x < objectGrid.length)) {
            if ((0 <= y) && (y < objectGrid[0].length)) {
                // System.out.println(object.getType() + ": " + x + ", " + y);
                if (0 <= index && index < objectGrid[x][y].size()) {
                    if (objectGrid[x][y].get(index).getType() != ' ') {
                        object = objectGrid[x][y].remove(index);
                        writeToTerminal(x, y);
                    }
                }
            }
        }
        return object;
    }

    private void writeToTerminal(int x, int y) { 
        char ch = objectGrid[x][y].peek().getType();
        terminal.write(ch, x, y);
        terminal.repaint();
    }

    public Stack<Displayable>[][] getObjectGrid() {
        return objectGrid;
    }
}
