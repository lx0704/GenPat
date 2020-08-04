/**
 * Copyright (C) SEI, PKU, PRC. - All Rights Reserved.
 * Unauthorized copying of this file via any medium is
 * strictly prohibited Proprietary and Confidential.
 * Written by Jiajun Jiang<jiajun.jiang@pku.edu.cn>.
 */

package mfix.core.node.diff;

import mfix.common.conf.Constant;
import mfix.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author: Jiajun
 * @date: 2018/9/25
 */
public class DiffTest extends TestCase {

    @Test
    public void test_extractFileDiff() {
        String srcFile = testbase + Constant.SEP + "src_Project.java";
        String tarFile = testbase + Constant.SEP + "tar_Project.java";
        List<Diff> diffs = Diff.extractFileDiff(srcFile, tarFile, AstDiff.class);
        Assert.assertTrue(diffs.size() == 7);
        for(int i = 0; i < 6; i++) {
            Assert.assertTrue(diffs.get(0).getMiniDiff().size() == 3);
        }

    }

    @Test
    public void test_extractFileDiff2() {
    	String basePath = "/home/xia/BugDetectionProject/3Clone/";
    	String[] years = {"NewDown2011-2017","NewDown2018","NewDown201811-12"};
    	for(int i = 0; i < 3; i++) {
    		String yearPath = basePath + years[i] + "/All";
    		File f = new File(yearPath);
    		String[] pathnames = f.list();
    		for (String pathname : pathnames) {
                String versionPath = yearPath + "/" + pathname;
                File versionF = new File(versionPath);
                String[] versionFiles = versionF.list();
                for (String versionFile:versionFiles) {
                	String versionFullPath = versionPath + "/" + versionFile;
                	if (Files.isDirectory(Paths.get(versionFullPath))) {
                		System.out.println(versionFullPath);
                	}
                }
            }
    		String srcFile = testbase + Constant.SEP + "src_Project.java";
    	    String tarFile = testbase + Constant.SEP + "tar_Project.java";
    	    List<Diff> diffs = Diff.extractFileDiff(srcFile, tarFile, TextDiff.class);
    	    System.out.println(diffs.size());
    	}
     
    }
}
