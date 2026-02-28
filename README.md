# Framework_Design
[![Maven Test Workflow](https://github.com/Rahul123456789mondal/Framework_Design/actions/workflows/main.yml/badge.svg)](https://github.com/Rahul123456789mondal/Framework_Design/actions/workflows/main.yml)
### 🚀 Featured Automation Project
* **[Selenium Framework Design](https://github.com/Rahul123456789mondal/Framework_Design)** - An end-to-end UI automation framework using Java, Selenium, and TestNG. 
  * Pipeline Status: [![Maven Test Workflow](https://github.com/Rahul123456789mondal/Framework_Design/actions/workflows/main.yml/badge.svg?branch=experiment)](https://github.com/Rahul123456789mondal/Framework_Design/actions/workflows/main.yml)
 
# ⚙️ E-Commerce UI Automation Framework

[![Maven Test Workflow](https://github.com/Rahul123456789mondal/Framework_Design/actions/workflows/main.yml/badge.svg?branch=experiment)](https://github.com/Rahul123456789mondal/Framework_Design/actions/workflows/main.yml)

<a href="https://readme-typing-svg.demolab.com?font=Fira+Code&weight=600&size=20&pause=1000&color=2EA043&center=false&vCenter=true&width=600&lines=Robust+End-to-End+UI+Automation;Built+with+Java+%26+Selenium+WebDriver;Page+Object+Model+(POM)+Architecture;Integrated+with+GitHub+Actions+CI%2FCD"><img src="https://readme-typing-svg.demolab.com?font=Fira+Code&weight=600&size=20&pause=1000&color=2EA043&center=false&vCenter=true&width=600&lines=Robust+End-to-End+UI+Automation;Built+with+Java+%26+Selenium+WebDriver;Page+Object+Model+(POM)+Architecture;Integrated+with+GitHub+Actions+CI%2FCD" alt="Typing SVG" /></a>

---

## 📖 Project Overview
This repository contains a highly scalable and maintainable UI automation framework designed to test an E-Commerce web application. It handles complex end-to-end user journeys, from login and dynamic product cart additions to secure checkout and order confirmation, alongside rigorous UI error validation.

## 🛠️ Tech Stack & Tools
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Selenium](https://img.shields.io/badge/-Selenium-%2343B02A?style=for-the-badge&logo=selenium&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-FF7F00?style=for-the-badge&logo=testng&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/GitHub_Actions-2088FF?style=for-the-badge&logo=github-actions&logoColor=white)

## ✨ Key Features
* **Page Object Model (POM):** Clean separation of web locators and page actions from test execution logic.
* **Cross-Browser Support:** Dynamic browser initialization for Chrome, Firefox, and Edge.
* **Headless CI/CD Execution:** Fully optimized to run on GitHub Actions Linux servers using headless Chrome arguments and `JavascriptExecutor` fallbacks to bypass UI interception.
* **Robust Synchronization:** Custom explicit wait utilities (`AbstractComponent`) to handle dynamic rendering and eliminate flaky tests.
* **Data-Driven Configuration:** Environment variables and URLs managed globally via `.properties` files.
* **Automated Reporting:** Integrates **ExtentReports** with automated screenshot captures hooked into TestNG listeners upon test failure.

---

## 📂 Project Architecture & File Structure

```text
Framework_Design/
├── .github/workflows/
│   └── main.yml                  # GitHub Actions CI/CD Pipeline configuration
├── src/main/java/
│   ├── AbstractComponents/       # Reusable wait strategies and common actions
│   ├── BaseConfig/               # Global properties loader (config.java)
│   ├── PageObject/               # POM classes (LandingPage, CartPage, CheckOutPage, etc.)
│   └── resourse/                 # globalData.properties (Browser, URL data)
├── src/test/java/
│   ├── Selenium_Framwork/        # Test classes (StandAloneTest, ErrorValidationTest)
│   ├── TestComponents/           # BaseTest setup, teardown, and driver initialization
│   └── Utility/                  # ExtentReportManager, TestListener, Screenshot handlers
├── pom.xml                       # Maven dependencies (Selenium, TestNG, ExtentReports)
└── README.md                     # Project documentation

## 🚀 Getting Started

Follow these steps to set up and run the framework on your local machine.

### 1️⃣ Prerequisites
Before you begin, ensure you have the following installed on your system:
* **Java Development Kit (JDK):** Version 11 or higher (Tested with Java 25).
* **Apache Maven:** Version 3.9.x or higher for dependency management.
* **Git:** To clone the repository.
* **IDE:** IntelliJ IDEA (recommended) or Eclipse.

### 2️⃣ Installation & Setup
**Step 1:** Clone the repository to your local machine:
```bash
git clone [https://github.com/Rahul123456789mondal/Framework_Design.git](https://github.com/Rahul123456789mondal/Framework_Design.git)


3️⃣ Configuration
The framework is designed to be highly configurable without changing the core Java code.
Navigate to the properties file located at: src/main/java/resourse/globalData.properties

Here, you can modify global test parameters such as:

browser=chrome (Change to firefox or edge to run cross-browser tests)
url=https://your-ecommerce-test-site.com
Country=India

4️⃣ Executing the Tests
Option A: Running via Maven (Command Line)
This is the recommended way to run tests, as it perfectly mimics how the tests execute in the GitHub Actions CI/CD pipeline.

Open your terminal in the project root folder and run:
To run tests normally (Browser UI will open):

Bash
mvn clean test
To run tests in Headless Mode (Background execution, no UI):

Bash
mvn clean test -Dheadless=true
Option B: Running via IDE (TestNG)

Navigate to src/test/java/Selenium_Framwork/framwork_design/.
Right-click on StandAloneTest.java or ErrorValidationTest.java.
Select Run 'TestName'.

5️⃣ Viewing the Automated Reports
Once the test execution is complete, the framework automatically generates a beautifully formatted HTML report using ExtentReports.

Navigate to the newly generated reports or target folder in your project directory.
Locate the .html report file.
Right-click the file, select Open In > Browser, and you will see a detailed breakdown of passed, failed, and skipped tests, complete with automatically attached screenshots for any UI failures!
This section is now much more comprehensive and acts as a mini-documentation guide for your project. 

Would you like me to help you create that `testng.xml` file next so you can easily group


