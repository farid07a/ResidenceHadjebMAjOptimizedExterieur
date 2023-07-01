
package residence;

/**
 *
 * @author Basma01
 */
class Main {
    
    public Main(){
      Home1 h=new Home1();
        Login log=new Login(h);
        log.setVisible(true);
        //h.setVisible(true);
    }
    public static void main(String[] args) {
      new Main();
    }
}
