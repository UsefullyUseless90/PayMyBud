package com.paymybuddy.paymybuddy.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Commissions_pmbTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link Commissions_pmb}
     *   <li>{@link Commissions_pmb#setMontant(double)}
     *   <li>{@link Commissions_pmb#setNumeroTransaction(int)}
     * </ul>
     */
    @Test
    void testConstructor() {
        Commissions_pmb actualCommissions_pmb = new Commissions_pmb();
        actualCommissions_pmb.setMontant(10.0);
        actualCommissions_pmb.setNumeroTransaction(10);
        assertEquals(10.0, actualCommissions_pmb.getMontant());
        assertEquals(10, actualCommissions_pmb.getNumeroTransaction());
    }
}

