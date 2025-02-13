package Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.BaseTest;


public class TestUtil extends BaseTest{
	public static int PAGE_LOAD_TIMEOUT = 20;
	public static int IMPLICIT_WAIT = 25;
	public static int EXPLICIT_WAIT = 20;
	
	static int c = 0;
	static int arrayindex = 0;
	static int k = 1;
	static int D = 1;
	static Workbook book;
	static Sheet sheet;
	static Row row;
	static Cell cell;

	public static Object[][] getTestData(String sheetName, String TestDataSheetPath) throws InvalidFormatException {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TestDataSheetPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		// System.out.println(sheet.getLastRowNum() + "--------" +
		// sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				FormulaEvaluator evaluator = book.getCreationHelper().createFormulaEvaluator();
				if (sheet.getRow(i + 1).getCell(k).getCellType() == CellType.FORMULA) {
					switch (evaluator.evaluateFormulaCell(sheet.getRow(i + 1).getCell(k))) {
					case BOOLEAN:
						data[i][k] = sheet.getRow(i + 1).getCell(k).getBooleanCellValue();
						break;
					case NUMERIC:
						data[i][k] = sheet.getRow(i + 1).getCell(k).getNumericCellValue();
						break;
					case STRING:
						data[i][k] = sheet.getRow(i + 1).getCell(k).getStringCellValue();
						break;
					default:
						break;
					}
					// System.out.println(data[i][k]);
				} else {
					data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				}
			}
		}
		return data;
	}

	

	public static String takeScreenshot(String screenshotName) throws IOException {
		String Date = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, see a folder "FailedTestCasesScreenshots" under src folder
		/*
		 * String destination = "./Reports/ODWeb/" + screenshotName + Date + ".png";
		 */
		String destination = ".//" + "Reports//ODWeb//" + screenshotName + Date + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return ".//" + screenshotName + Date + ".png";
	}

	public static void explicitWait_elementToBeClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void explicitWait_elementVisibility(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
		wait.until(ExpectedConditions.visibilityOf(element));
	}






}

