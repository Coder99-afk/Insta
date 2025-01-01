package com.wbd.insta.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;



public class BaseEntityTest {

    private BaseEntity baseEntity;

    @BeforeEach
    public void setUp() {
        baseEntity = new BaseEntity() {};
    }

    @Test
    public void testDefaultIdValue() {
        assertEquals(0, baseEntity.getId());
    }

    @Test
    public void testSetAndGetId() {
        long expectedId = 1L;
        baseEntity.setId(expectedId);
        assertEquals(expectedId, baseEntity.getId());
    }

    @Test
    public void testGeneratedId() {
        baseEntity.setId(100L);
        assertNotNull(baseEntity.getId());
        assertEquals(100L, baseEntity.getId());
    }
}