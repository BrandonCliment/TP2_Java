package ticketmachine;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@Before
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation
	public void priceIsCorrectlyInitialized() {
		// Paramètres : message si erreur, valeur attendue, valeur réelle
		assertEquals("Initialisation incorrecte du prix", PRICE, machine.getPrice());
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	public void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
		assertEquals("La balance n'est pas correctement mise à jour", 10 + 20, machine.getBalance()); // Les montants ont été correctement additionnés               
	}
        @Test
        //S3: On n'imprime pas de ticket si le montant est insuffisant
        public void montantInsuffisant(){
                assertFalse(machine.printTicket());
                
        }
        @Test
        //S4: On imprime le ticket si le montant est suffisant
        public void montantSuffisant(){
                machine.insertMoney(PRICE);
                assertTrue(machine.printTicket());
        }
        
        @Test
        //S5: On imprime le ticket si le montant est suffisant
        public void balanceDecremente(){
                machine.insertMoney(PRICE);
                assertEquals(PRICE, PRICE);
                machine.printTicket();
                assertEquals(0, machine.getBalance());
        }
        
        @Test
        //S6: Montant collecté MaJ après impression
        public void montantMaJ(){
            machine.insertMoney(PRICE);
            assertEquals("Montant inchangé avant impression",0, machine.getTotal());
            machine.printTicket();
            assertEquals("Montant total mis à jour avec print",50, machine.getTotal());
        }
       
        @Test
        //S7:refund rend la monnaie
        public void refundMoney(){
            machine.insertMoney(PRICE);
            assertEquals("Le refund rend correctement la monnaie",machine.refund(), PRICE);
        }
        
        @Test
        //S8: refund remet la balance à zero
        public void refundTo0(){
            machine.insertMoney(PRICE);
            machine.refund();
            assertEquals(machine.getBalance(), 0);
        }
        
        @Test (expected=IllegalArgumentException.class)
        //S9:on ne peut pas insérer un montant negatif
        public void noNegative(){
            machine.insertMoney(-10);
        }
        
        @Test (expected=IllegalArgumentException.class)
        //S10:on ne peut pas créer de ticket avec un prix neg
        public void prixTicketPos(){
            machine=new TicketMachine(-50);
        }
        

}
