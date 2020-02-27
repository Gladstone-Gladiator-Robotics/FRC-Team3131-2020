package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Button;

public class DirectionalPad extends Button {
        
    /* Instance Values */
    private final   XboxController    parent;
    
    public final    Button      up;
    public final    Button      upRight;
    public final    Button      right;
    public final    Button      downRight;
    public final    Button      down;
    public final    Button      downLeft;
    public final    Button      left;
    public final    Button      upLeft;
    
    
    
    /**
     * Constructor
     * 
     * @param controller
     */
    DirectionalPad(final XboxController controller) {

      /* Initialize */
      this.parent = controller;
        this.up         = new DPadButton(this, DPAD.UP);
        this.upRight    = new DPadButton(this, DPAD.UP_RIGHT);
        this.right      = new DPadButton(this, DPAD.RIGHT);
        this.downRight  = new DPadButton(this, DPAD.DOWN_RIGHT);
        this.down       = new DPadButton(this, DPAD.DOWN);
        this.downLeft   = new DPadButton(this, DPAD.DOWN_LEFT);
        this.left       = new DPadButton(this, DPAD.LEFT);
        this.upLeft     = new DPadButton(this, DPAD.UP_LEFT);
    }
    

    
    /**
     * This class is used to represent each of the 8 values a
     * dPad has as a button.
     */
    public static class DPadButton extends Button {
        
        /* Instance Values */
        private final DPAD              direction;
        private final DirectionalPad    parent;
        
        
        
        /**
         * Constructor
         * @param parent
         * @param dPad
         */
        DPadButton(final DirectionalPad parent, final DPAD dPadDirection) {
            
            /* Initialize */
            this.direction  = dPadDirection;
            this.parent     = parent;
        }
        
        
        
        /* Extended Methods */
        @Override
        public boolean get() {
            return parent.getAngle() == direction.value;
        }
    }

    public int getAngle() {
      return angle();
    }
    
    private int angle() {
      return parent.getPOV();
    }
    
    public static enum DPAD {
      UP          (0),
      UP_RIGHT    (45),
      RIGHT       (90),
      DOWN_RIGHT  (135),
      DOWN        (180),
      DOWN_LEFT   (225),
      LEFT        (270),
      UP_LEFT     (315);
      
      
      
      /* Instance Value */
      private int value;
      
      
      
      /**
       * Constructor
       * @param value
       */
      DPAD(final int value) {
          this.value = value;
      }
    }
  }