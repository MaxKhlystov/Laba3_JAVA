
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileOutputStream;
import java.util.Scanner;

public class FileReaderFromTxt {

    public static boolean checkWriteNumber = false;
    public static boolean checkWriteArray = false;
    public static double FileReadingNum() {
        double numb = 0;
        String filePath = inputFileName();
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    numb = Double.parseDouble(line.trim()); // trim() удаляет пробелы
                    System.out.println("Прочитанное число: " + numb);
                    checkWriteNumber = true;
                } catch (NumberFormatException e) {
                    System.err.println("Ошибка: некорректный формат числа в строке : " + line);
                    checkWriteNumber = false;
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка: Неправильное имя файла или файл не существует.");
            checkWriteNumber = false;
        }
        return numb;
    }

    public static List<Integer> FileReadingArray() throws IOException, NumberFormatException {
        String filePath = inputFileName();
        List<Integer> array = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            if (line != null) {
                String[] numbers = line.trim().split("\\s+");
                for (String number : numbers) {
                    array.add(Integer.parseInt(number));
                }
            }
            checkWriteArray = true;
        }catch (IOException e) {
            System.err.println("Ошибка: Неправильное имя файла или файл не существует.");
            checkWriteArray = false;
        }
        return array;
    }

    public static void writeArrayToFile() {
        String filePath = inputFileName();
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (int num : taskArray.newArray) {
                writer.println(num);
            }
            System.out.println("Массив успешно записан в файл: " + filePath);
        } catch (IOException e) {
            System.err.println("Ошибка: Неправильное имя файла или файл не существует.");
        }
    }

    public static void writeNumberToFile(double numb) {
        numberWord.findNumber(numb);
        String filePath = inputFileName();
        String numberInWords = numberWord.thousand + numberWord.hundred + numberWord.dozen + numberWord.unit + "руб. " + numberWord.dozenFloat + numberWord.unitFloat + "коп.";

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("Заданное число: " + numb);
            writer.println("Заданное число словами: " + numberInWords);
            System.out.println("Число успешно записано в файл: " + filePath);
        } catch (IOException e) {
            System.err.println("Ошибка: Неправильное имя файла или файл не существует.");
        }
    }

    public static void writeNumberToBinaryFile(double numb) {
        numberWord.findNumber(numb);
        String numberInWords = numberWord.thousand + numberWord.hundred + numberWord.dozen + numberWord.unit + "rub. " + numberWord.dozenFloat + numberWord.unitFloat + "kop.";
        String filePath = inputFileName();

        try (DataOutputStream dos = new DataOutputStream((new FileOutputStream(filePath)))) {
            // Записываем число напрямую как double
            dos.writeDouble(numb);
            // Записываем длину строки
            dos.writeInt(numberInWords.length());
            // Записываем строку в байтах
            dos.writeBytes(numberInWords);

            System.out.println("Число успешно сохранено в бинарный файл: " + filePath);
        } catch (IOException e) {
            System.err.println("Ошибка: Неправильное имя файла или файл не существует.");
        }
    }

    public static void writeArrayToBinaryFile(int size, taskArray newArray) {
        String filePath = inputFileName();
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(filePath))) {
            dos.writeInt(size);
            for (int value : taskArray.newArray) {
                dos.writeInt(value);
            }
            System.out.println("Массив успешно сохранен в бинарный файл: " + filePath);
        } catch (IOException e) {
            System.err.println("Ошибка: Неправильное имя файла или файл не существует.");
        }
    }

    public static String inputFileName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя файла для чтения или сохранения (например, array.bin): ");
        return scanner.nextLine();
    }
}
