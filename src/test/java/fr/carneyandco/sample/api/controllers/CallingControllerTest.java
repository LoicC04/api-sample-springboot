package fr.carneyandco.sample.api.controllers;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CallingControllerTest {

    private static final Logger LOG = LoggerFactory.getLogger(CallingControllerTest.class);

    @Test
    public void shouldSuccessWhenCallOther() {
        // GIVEN
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.any())).thenReturn(ResponseEntity.accepted().body("My response"));
        CallingController cc = new CallingController(restTemplate);

        // WHEN
        String result = cc.callAnotherService();

        // THEN
        Assert.assertNotNull(result);
        LOG.info("{}", result);
    }

}
