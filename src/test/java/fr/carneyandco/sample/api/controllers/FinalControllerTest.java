package fr.carneyandco.sample.api.controllers;

import fr.carneyandco.sample.api.services.MySimpleService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class FinalControllerTest {

    @Test
    public void shouldSuccessWhenHello() {
        // GIVEN
        MySimpleService mySimpleService = Mockito.mock(MySimpleService.class);
        Mockito.when(mySimpleService.traitement()).thenReturn(true);

        FinalController finalController = new FinalController(mySimpleService);


        // WHEN
        String result = finalController.hello();

        // THEN
        Assert.assertNotNull(result);
    }

}
