package lab6;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class FrameAnimation2 extends FrameAnimation {
    protected FrameAnimation.AnimationCanvas createAnimationCanvas(){
        return new AnimationCanvas2();
    }
    
    class AnimationCanvas2 extends FrameAnimation.AnimationCanvas{
        protected void paint(Graphics g){
            int clipX = g.getClipX();
            int clipY = g.getClipY();
            int clipWidth = g.getClipWidth();
            int clipHeight = g.getClipHeight();
            g.setColor(background);
            g.fillRect(clipX, clipY, clipWidth, clipHeight);
            g.setColor(foreground);
            synchronized (this){
                for (int i = 0, count = blocks.length; i < count; i++) {
                    g.fillRect(blocks[i].x, blocks[i].y, SIZE, SIZE);
                }
            }
        }
        public synchronized void moveAllBlocks(){
            for (int i = 0, count = blocks.length; i < count; i++){
                Block block = blocks[i];
                repaint(block.x, block.y, SIZE, SIZE);
                blocks[i].move();
                repaint(block.x, block.y, SIZE, SIZE);
            }
        }
    }
}