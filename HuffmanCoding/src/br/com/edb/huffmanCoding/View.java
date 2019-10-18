package br.com.edb.huffmanCoding;


import javax.swing.*;
import java.awt.*;

public class View extends JComponent {
    private BinaryTree arvore;
    private int nodeSize = 30;

    public View(BinaryTree arvore) {
        this.arvore = arvore;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        drawNode(graphics, this.arvore.peek(), getWidth()/2, 0, 0);
    }

    private void drawNode(Graphics graphics, Node node, int x, int y, int level){
        graphics.drawOval(x, y, nodeSize, nodeSize);
        graphics.drawString(node.getLetter(), x+8, y+20);

        if(node.getLeft() != null){
            int childX = x-40;
            int childY = y+40;
            graphics.drawLine(x + nodeSize/2 , y + nodeSize, childX + nodeSize/2, childY);
            drawNode(graphics, node.getLeft(), childX, childY, level+1);
        }

        if(node.getRight() != null){
            int childX = x+40;
            int childY = y+40;
            graphics.drawLine(x + nodeSize/2 , y + nodeSize, childX + nodeSize/2, childY);
            drawNode(graphics, node.getRight(), childX, childY, level+1);
        }
    }
}