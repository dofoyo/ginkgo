package com.rhb.ginkgo.util;

import org.junit.Assert;
import org.junit.Test;

public class UtilTest {
	@Test
	public void test(){
		String str1 = "create:{\"name\":\"集团ERP\",\"id\":\"fa7a4ae9-356e-4ce1-be0c-876947f89b34\"}";
		String str = "<p>create:{&quot;name&quot;:&quot;集团ERP&quot;,&quot;id&quot;:&quot;<span style=\"font-size:14px;font-family:&#39;Calibri&#39;,&#39;sans-serif&#39;\">fa7a4ae9-356e-4ce1-be0c-876947f89b34</span>&quot;}</p>";
		
		String str2 = Convert.html2Str(str);
		
		System.out.println(str1);
		System.out.println(str2);
		
		Assert.assertTrue(str1.equals(str2));;
	}
}
