import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Flappybird here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Flappybird extends Actor
{
    private double g = 3;
    private int y = 200;
    private boolean haspressed = false;
    private boolean isalive = true;
    private boolean isacross = false;
    private boolean hasaddscore = false;
    public Flappybird(){
        GreenfootImage image = getImage();         
        image.scale(50, 40);
    }
    public void act()
    {
        // Jika tekan spasi, koordinat y akan berkurang dan terbang ke atas
        if(spacePressed()){
            g = -1.5;
        }
        g +=0.1; //Nilai g meningkat 0,1 setiap saat
        y +=g; //Nilai y ditambahkan dg nilai g menyebabkan flappyBird bergerak k bawah
        setLocation(getX(), (int)(y));//mengatur posisi flappyBird ke koordinat
        //Jika menabrak pipa maka flappybird mati
        if (isTouchingpipe()){
            isalive = false;
        }
        //apabila flappybird menyentuh pipe 
        if(!isalive){
            getWorld().addObject(new Gameover() ,300 , 100);    
            getWorld().removeObject(this);
        }
        //FlappyBird melewati pipe maka score akan bertambah dan memutar sound
        if(!hasaddscore && isacross && isalive){
            Greenfoot.playSound("score.mp3");
            Score.add(1);
        }
        hasaddscore = isacross;
    }
    public boolean spacePressed(){
        boolean pressed = false;
        if(Greenfoot.isKeyDown("Space")){
            if(!haspressed){//pemberian suara
                Greenfoot.playSound("flay.mp3");
                pressed = true;
            }
            haspressed = true;
        }else{
            haspressed = false;
        }
        return haspressed;
    }
    //pemberian suara ketika menabrak pipa dan jatuh
    public boolean isTouchingpipe(){
        isacross = false;
            for(Pipe pipe : getWorld().getObjects(Pipe.class)){
                if(Math.abs(pipe.getX() - getX()) < 69){
                if(Math.abs(pipe.getY() + 30 - getY()) > 37){
                    Greenfoot.playSound("peng.mp3");
                    isalive = false;
                }
                isacross = true;
            }
            
        }
        return !isalive;
    }
}
