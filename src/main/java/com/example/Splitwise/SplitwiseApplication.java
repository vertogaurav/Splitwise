package com.example.Splitwise;

import com.example.Splitwise.models.*;
import com.example.Splitwise.service.GroupManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class SplitwiseApplication {

	public static void main(String[] args) {

		GroupManager expenseManager = new GroupManager();

		expenseManager.addUser(new User("u1", "User1", "gaurav@workat.tech", "9876543210"));
		expenseManager.addUser(new User("u2", "User2", "sagar@workat.tech", "9876543210"));
		expenseManager.addUser(new User("u3", "User3", "hi@workat.tech", "9876543210"));
		expenseManager.addUser(new User("u4", "User4", "mock-interviews@workat.tech", "9876543210"));

		Scanner scanner = new Scanner(System.in);
		int input=scanner.nextInt();

		while (input>0) {
			String command = scanner.nextLine();
			String[] commands = command.split(" ");
			String commandType = commands[0];

			switch (commandType) {
				case "SHOW":
					if (commands.length == 1) {
						expenseManager.showAllBalance();
					} else {
						expenseManager.showUserBalance(commands[1]);
					}
					break;
				case "EXPENSE":
					String paidBy = commands[1];
					Double amount = Double.parseDouble(commands[2]);
					int noOfUsers = Integer.parseInt(commands[3]);
					String expenseType = commands[4 + noOfUsers];
					List<Split> splits = new ArrayList<>();
					switch (expenseType) {
						case "EQUAL":
							for (int i = 0; i < noOfUsers; i++) {
								splits.add(new EqualSplit(expenseManager.userMap.get(commands[4 + i])));
							}
							expenseManager.addGroup(GroupType.EQUAL, amount, paidBy, splits);
							break;
						case "EXACT":
							for (int i = 0; i < noOfUsers; i++) {
								splits.add(new ExactSplit(expenseManager.userMap.get(commands[4 + i]), Double.parseDouble(commands[5 + noOfUsers + i])));
							}
							expenseManager.addGroup(GroupType.EXACT, amount, paidBy, splits);
							break;

					}
					break;
			}
			input--;
		}

	}

}
