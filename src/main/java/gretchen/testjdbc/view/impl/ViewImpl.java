package gretchen.testjdbc.view.impl;

import gretchen.testjdbc.controller.ShopServiceController;
import gretchen.testjdbc.dto.BuyRequestDTO;
import gretchen.testjdbc.view.View;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class ViewImpl implements View {
    private Scanner scanner;
    private PrintStream out;
    private ShopServiceController shopServiceController;
    private boolean isInterrupted;

    public ViewImpl(Scanner scanner, PrintStream out, ShopServiceController shopServiceController) {
        this.scanner = scanner;
        this.out = out;
        this.shopServiceController = shopServiceController;
    }

    @Override
    public void start() {
        while (!isInterrupted) {
            out.print("Введите через пробел ID покупателя и ID товара\n");
            out.print("Для выхода из программы введите слово \"выход\"\n");
            handleMsg(scanner.nextLine());
        }
    }

    private void handleMsg(String msg) {
        String[] command = msg.split(" ");
        BuyRequestDTO buyRequestDTO = new BuyRequestDTO();

        if (msg.toLowerCase(Locale.ROOT).contains("выход")){
            isInterrupted = true;
        } else if (command.length != 2) {
            buyRequestDTO.setMessage("Вы ввели неверные данные");
            out.println(buyRequestDTO.getMessage());
        } else {
            try {
                int[] commandID = Arrays.stream(command).mapToInt(Integer::parseInt).toArray();
                buyRequestDTO = shopServiceController.buyProduct(commandID[0], commandID[1]);
                out.println(buyRequestDTO.getMessage());
            } catch (NumberFormatException e) {
                out.println("Данные должны быть введены в числовом формате");
            }
        }
    }
}
