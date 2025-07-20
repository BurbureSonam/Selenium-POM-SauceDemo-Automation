# 🧪 SauceDemo Automation Framework

This repository contains an **end-to-end automation testing framework** for [SauceDemo](https://www.saucedemo.com/) built using a **Hybrid Framework** combining:

- ✅ TestNG
- ✅ Cucumber BDD
- ✅ Page Object Model (POM)
- ✅ ExtentReports
- ✅ Maven
- ✅ Selenium WebDriver

---

## 📁 Project Structure

SauceDemo-Automation/
│
├── src/
│ ├── main/java/
│ │ ├── pages/ # Page classes (CartPage,HomePage,LoginPage etc.)
│ │ └── utils/ # BaseTest,ExtentReport Manager, Hooks, TestListeners, Screenshot utils
│ └── test/java/
│ ├── runner/ # Cucumber TestRunner
│ ├── StepDefinitions/ # Cucumber StepDefs using POM
│ └── tests/ # TestNG test cases
│
├── src/test/resources/
│ └── featureFile/ # Cucumber .feature files
│
├── reports/ # ExtentReports
├── Screenshots/ # Screenshots on failure
├── pom.xml # Maven dependencies
└── testng.xml # TestNG suite runner

## 🔍 Features

- ✅ **Hybrid Automation Framework** (TestNG + Cucumber BDD)
- ✅ **POM Design Pattern** for modular test scripts
- ✅ **Listeners** to generate dynamic reports and logs
- ✅ **Screenshots on Failure**
- ✅ **Extent Reports Integration** with detailed HTML output

📸 Extent Report Preview
<img width="1356" height="679" alt="Extent Report Of TestNG Framework" src="https://github.com/user-attachments/assets/95de7036-2fce-449e-8191-9138588612c9" />

🚀 How to Run the Project

 ▶️ Run using TestNG
Right-click on testng.xml > Run As > TestNG Suite

▶️ Run using Cucumber
Right-click on TestRunner.java > Run As > JUnit

🧰 Tools & Technologies Used
Tool/Tech	Purpose
Java	Programming Language
Selenium WebDriver	Browser Automation
TestNG	Test execution
Cucumber BDD Testing Framework
Extent Reports	Reporting
Maven	Build Management Tool
Page Object Model	Design Pattern for UI Testing

 Author
Sonam Burbure
📍 India
GitHub: @BurbureSonam
