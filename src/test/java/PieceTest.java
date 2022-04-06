import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.*;
class PieceTest {
    @Test
    public void test_Piece() {
        // Piece has a constructor
        // but we should not be able to create a Piece
        System.out.println("Testing Piece Class");
        try {
            Class<Piece> clazz = Piece.class;
            Constructor<Piece> ctor = clazz.getDeclaredConstructor(char.class, String.class,
                    boolean.class, boolean.class);
            try {
                ctor.newInstance('H', "Red", false, false);
                fail("Should not be able to create an instance of Piece");
            } catch (Exception e) {
                // should happen
            }

            System.out.println("Testing Piece Constructor passes");

            // speak should not be implemented in the Piece class
            Method m = clazz.getDeclaredMethod("speak", new Class[]{});
            assertEquals(void.class, m.getReturnType());
           /*
            assertTrue(Modifier.isAbstract(m.getModifiers()));

            // validMovePath should not be implemented in the Piece class
            m = clazz.getDeclaredMethod("validMovePath", int.class,int.class,int.class,int.class);
            assertEquals(boolean.class, m.getReturnType());
            assertTrue(Modifier.isAbstract(m.getModifiers()));
*/
        } catch (Exception e) {
            // not supposed to happen
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    @Test
    public void test_Minion() {
        System.out.println("Testing Minion Piece");
        PieceMinion stuart = new PieceMinion('M',
                "Blu",0,0,
                true,true);
        // check inheritance
        assertTrue(stuart instanceof Piece);

        try {
            // speak should be implemented
            Method m = PieceMinion.class.getMethod("speak", new Class[]{});
            assertEquals(void.class, m.getReturnType());
            assertFalse(Modifier.isAbstract(m.getModifiers()));

            // validPath should be implemented
            m = PieceMinion.class.getDeclaredMethod("validMovePath", int.class,int.class,int.class,int.class);
            assertEquals(boolean.class, m.getReturnType());
            assertFalse(Modifier.isAbstract(m.getModifiers()));

            // spawn should be implemented
            m = PieceMinion.class.getDeclaredMethod("spawn", new Class[]{});
            assertEquals(PieceMinion.class, m.getReturnType());
            assertFalse(Modifier.isAbstract(m.getModifiers()));

        } catch (Exception e) {
            // not supposed to happen
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void test_Buzz() {
        System.out.println("Testing Buzz Lightyear Class");
        PieceBuzz buzz = new PieceBuzz('B',"GRN",
                0,0,true,
                false, true);
        // check inheritance
        assertTrue(buzz instanceof Piece);

        try {
            // speak should be implemented
            Method m = PieceBuzz.class.getMethod("speak", new Class[]{});
            assertEquals(void.class, m.getReturnType());
            assertFalse(Modifier.isAbstract(m.getModifiers()));

            // validPath should be implemented
            m = PieceBuzz.class.getDeclaredMethod("validMovePath", int.class,int.class,int.class,int.class);
            assertEquals(boolean.class, m.getReturnType());
            assertFalse(Modifier.isAbstract(m.getModifiers()));

            // getNumAttacks should be implemented
            m = PieceBuzz.class.getDeclaredMethod("getNumAttacks", new Class[]{});
            assertEquals(int.class, m.getReturnType());
            assertFalse(Modifier.isAbstract(m.getModifiers()));

            // setNumAttacks should be implemented
            m = PieceBuzz.class.getDeclaredMethod("setNumAttacks", int.class);
            assertEquals(void.class, m.getReturnType());
            assertFalse(Modifier.isAbstract(m.getModifiers()));
        } catch (Exception e) {
            // not supposed to happen
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void test_BlueHen() {
        System.out.println("Testing Blue Hen Class");
        PieceBlueHen youdee = new PieceBlueHen('H', "Red", 5, 2,true, true);
        assertTrue(youdee instanceof Piece);

        try {
            // speak should be implemented
            Method m = PieceBlueHen.class.getMethod("speak", new Class[]{});
            assertEquals(void.class, m.getReturnType());
            assertFalse(Modifier.isAbstract(m.getModifiers()));

            // validPath should be implemented
            m = PieceBlueHen.class.getDeclaredMethod("validMovePath", int.class,int.class,int.class,int.class);
            assertEquals(boolean.class, m.getReturnType());
            assertFalse(Modifier.isAbstract(m.getModifiers()));

            // spawn should be implemented
            m = PieceBlueHen.class.getDeclaredMethod("spawn", new Class[]{});
            assertEquals(PieceBlueHen.class, m.getReturnType());
            assertFalse(Modifier.isAbstract(m.getModifiers()));

            // getNumAttacked should be implemented
            m = PieceBlueHen.class.getDeclaredMethod("getNumAttacks", new Class[]{});
            assertEquals(int.class, m.getReturnType());
            assertFalse(Modifier.isAbstract(m.getModifiers()));

            // getNumRecruits should be implemented
            m = PieceBlueHen.class.getDeclaredMethod("getNumRecruits", new Class[]{});
            assertEquals(int.class, m.getReturnType());
            assertFalse(Modifier.isAbstract(m.getModifiers()));

            // setNumAttacks should be implemented
            m = PieceBlueHen.class.getDeclaredMethod("setNumAttacks", int.class);
            assertEquals(void.class, m.getReturnType());
            assertFalse(Modifier.isAbstract(m.getModifiers()));

            // setNumRecruits should be implemented
            m = PieceBlueHen.class.getDeclaredMethod("setNumRecruits", int.class);
            assertEquals(void.class, m.getReturnType());
            assertFalse(Modifier.isAbstract(m.getModifiers()));

        } catch (Exception e) {
            // not supposed to happen
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
/*
    @Test
    public void test_EvilMinion() {
        System.out.println("Testing Evil Minion Piece");
        PieceEvilMinion bob = new PieceEvilMinion('E',
                "Blu",4,2,3,
                true,false);
        // check inheritance
        assertTrue(bob instanceof PieceMinion);

        assertEquals(4,bob.getNumRecruits());
        assertEquals(2,bob.getNumAttacks());
        assertEquals(3,bob.getNumTimesSpawned());
        assertTrue(bob.isHidden());
        assertFalse(bob.isOriginal());

        try {
            // speak should be implemented
            Method m = PieceEvilMinion.class.getMethod("speak", new Class[]{});
            assertEquals(void.class, m.getReturnType());
            assertFalse(Modifier.isAbstract(m.getModifiers()));

            // validPath should be implemented
            m = PieceMinion.class.getDeclaredMethod("validMovePath", int.class,int.class,int.class,int.class);
            assertEquals(boolean.class, m.getReturnType());
            assertFalse(Modifier.isAbstract(m.getModifiers()));

        } catch (Exception e) {
            // not supposed to happen
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    */

}