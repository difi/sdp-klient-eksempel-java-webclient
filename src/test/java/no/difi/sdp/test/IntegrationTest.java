package no.difi.sdp.test;

import no.difi.sdp.client2.KlientKonfigurasjon;
import no.difi.sdp.client2.SikkerDigitalPostKlient;
import no.difi.sdp.client2.domain.*;
import no.difi.sdp.client2.domain.digital_post.DigitalPost;
import no.difi.sdp.client2.domain.kvittering.ForretningsKvittering;
import no.difi.sdp.client2.domain.kvittering.KvitteringForespoersel;
import no.difi.sdp.testavsender.configuration.util.CryptoUtil;
import no.digipost.api.representations.Organisasjonsnummer;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URISyntaxException;
import java.security.KeyStore;
import java.util.UUID;

public class IntegrationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(IntegrationTest.class);

    @Test
    @Ignore // This test should only be run explicitly
    public void send_message_and_poll_for_receipts_for_60_seconds() throws InterruptedException, URISyntaxException {
        // Minimal example for sending post and recieving receipt using a actual integration with a test environment for meldingsformidler
        CryptoUtil cryptoUtil = new CryptoUtil();
        KeyStore keyStore = cryptoUtil.loadKeystore("JKS", "avsender.jks", "changeit");
        Noekkelpar noekkelpar = Noekkelpar.fraKeyStore(keyStore, "991825827-difi", "changeit");
        LOGGER.info("Avsender X509 certificate subject DN " + noekkelpar.getVirksomhetssertifikat().getX509Certificate().getSubjectDN());
        LOGGER.info("Avsender X509 certificate issuer DN " + noekkelpar.getVirksomhetssertifikat().getX509Certificate().getIssuerDN());
        DatabehandlerOrganisasjonsnummer databehandlerOrgnr = AktoerOrganisasjonsnummer.of("991825827").forfremTilDatabehandler();
        Databehandler avsender = Databehandler.builder(databehandlerOrgnr, noekkelpar).build();
        KlientKonfigurasjon klientKonfigurasjon = KlientKonfigurasjon.builder("https://qaoffentlig.meldingsformidler.digipost.no/api/").build();
        //KlientKonfigurasjon klientKonfigurasjon = KlientKonfigurasjon.builder().meldingsformidlerRoot("https://qa.meldingsformidler.digipost.no/api/").build();
        SikkerDigitalPostKlient postklient = new SikkerDigitalPostKlient(avsender, klientKonfigurasjon);
        Sertifikat mottakerSertifikat = Sertifikat.fraBase64X509String("MIIE7jCCA9agAwIBAgIKGBZrmEgzTHzeJjANBgkqhkiG9w0BAQsFADBRMQswCQYDVQQGEwJOTzEdMBsGA1UECgwUQnV5cGFzcyBBUy05ODMxNjMzMjcxIzAhBgNVBAMMGkJ1eXBhc3MgQ2xhc3MgMyBUZXN0NCBDQSAzMB4XDTE0MDQyNDEyMzA1MVoXDTE3MDQyNDIxNTkwMFowVTELMAkGA1UEBhMCTk8xGDAWBgNVBAoMD1BPU1RFTiBOT1JHRSBBUzEYMBYGA1UEAwwPUE9TVEVOIE5PUkdFIEFTMRIwEAYDVQQFEwk5ODQ2NjExODUwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCLCxU4oBhtGmJxXZWbdWdzO2uA3eRNW/kPdddL1HYl1iXLV/g+H2Q0ELadWLggkS+1kOd8/jKxEN++biMmmDqqCWbzNdmEd1j4lctSlH6M7tt0ywmXIYdZMz5kxcLAMNXsaqnPdikI9uPJZQEL3Kc8hXhXISvpzP7gYOvKHg41uCxu1xCZQOM6pTlNbxemBYqvES4fRh2xvB9aMjwkB4Nz8jrIsyoPI89i05OmGMkI5BPZt8NTa40Yf3yU+SQECW0GWalB5cxaTMeB01tqslUzBJPV3cQx+AhtQG4hkOhQnAMDJramSPVtwbEnqOjQ+lyNmg5GQ4FJO02ApKJTZDTHAgMBAAGjggHCMIIBvjAJBgNVHRMEAjAAMB8GA1UdIwQYMBaAFD+u9XgLkqNwIDVfWvr3JKBSAfBBMB0GA1UdDgQWBBQ1gsJfVC7KYGiWVLP7ZwzppyVYTTAOBgNVHQ8BAf8EBAMCBLAwFgYDVR0gBA8wDTALBglghEIBGgEAAwIwgbsGA1UdHwSBszCBsDA3oDWgM4YxaHR0cDovL2NybC50ZXN0NC5idXlwYXNzLm5vL2NybC9CUENsYXNzM1Q0Q0EzLmNybDB1oHOgcYZvbGRhcDovL2xkYXAudGVzdDQuYnV5cGFzcy5uby9kYz1CdXlwYXNzLGRjPU5PLENOPUJ1eXBhc3MlMjBDbGFzcyUyMDMlMjBUZXN0NCUyMENBJTIwMz9jZXJ0aWZpY2F0ZVJldm9jYXRpb25MaXN0MIGKBggrBgEFBQcBAQR+MHwwOwYIKwYBBQUHMAGGL2h0dHA6Ly9vY3NwLnRlc3Q0LmJ1eXBhc3Mubm8vb2NzcC9CUENsYXNzM1Q0Q0EzMD0GCCsGAQUFBzAChjFodHRwOi8vY3J0LnRlc3Q0LmJ1eXBhc3Mubm8vY3J0L0JQQ2xhc3MzVDRDQTMuY2VyMA0GCSqGSIb3DQEBCwUAA4IBAQCe67UOZ/VSwcH2ov1cOSaWslL7JNfqhyNZWGpfgX1c0Gh+KkO3eVkMSozpgX6M4eeWBWJGELMiVN1LhNaGxBU9TBMdeQ3SqK219W6DXRJ2ycBtaVwQ26V5tWKRN4UlRovYYiY+nMLx9VrLOD4uoP6fm9GE5Fj0vSMMPvOEXi0NsN+8MUm3HWoBeUCLyFpe7/EPsS/Wud5bb0as/E2zIztRodxfNsoiXNvWaP2ZiPWFunIjK1H/8EcktEW1paiPd8AZek/QQoG0MKPfPIJuqH+WJU3a8J8epMDyVfaek+4+l9XOeKwVXNSOP/JSwgpOJNzTdaDOM+uVuk75n2191Fd7");
        LOGGER.info("Mottaker X509 certificate subject DN " + mottakerSertifikat.getX509Certificate().getSubjectDN());
        LOGGER.info("Mottaker X509 certificate issuer DN " + mottakerSertifikat.getX509Certificate().getIssuerDN());
        Mottaker mottaker = Mottaker.builder("04036125433", "ove.jonsen#6K5A", mottakerSertifikat, Organisasjonsnummer.of("984661185")).build();
        DigitalPost digitalPost = DigitalPost.builder(mottaker, "ikkeSensitivTittel").build();
        final File testFile = new File(getClass().getResource("/1-Test-PDF.pdf").toURI());
        Dokument hoveddokument = Dokument.builder("Document title", testFile).mimeType("application/pdf").build();
        Dokumentpakke dokumentpakke = Dokumentpakke.builder(hoveddokument).build();
        AvsenderOrganisasjonsnummer avsenderOrgnr = AktoerOrganisasjonsnummer.of("984661185").forfremTilAvsender();
        Avsender behandlingsansvarlig = Avsender.builder(avsenderOrgnr).build();
        String mpc = UUID.randomUUID().toString();
        Forsendelse forsendelse = Forsendelse.digital(behandlingsansvarlig, digitalPost, dokumentpakke).mpcId(mpc).build();
        postklient.send(forsendelse);
        LOGGER.info("Post sent, waiting for 10 seconds before attemting to retrieve receipt.");
        for (int i = 0; i < 6; i++) {
            Thread.sleep(1000 * 10);
            ForretningsKvittering forretningsKvittering = postklient.hentKvittering(KvitteringForespoersel.builder(Prioritet.NORMAL).mpcId(mpc).build());
            if (forretningsKvittering == null) {
                LOGGER.info("No receipt available, waiting for 10 seconds before attemting to retrieve receipt.");
            } else {
                LOGGER.info("Receipt retrieved. Post delivered: " + forretningsKvittering.getReferanseTilMeldingSomKvitteres());
                return;
            }

        }
        LOGGER.info("Tried to retrieve receipt for 60 seconds. No receipt retrieved. Gives up.");
    }

}
