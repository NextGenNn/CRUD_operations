package ru.sfedu.log4jproject;

import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void main() {
        Client client = new Client();
        client.logBasicSystemInfo();
    }
}