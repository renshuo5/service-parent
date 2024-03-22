package com.renshuo.nio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ServiceNioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceNioApplication.class, args);
	}
	@PostConstruct
	public void config() {
		List<String> classNames = new ArrayList<>();

		String annotationName = "com.bitnei.cloud.common.auth.annotation.RequiresPermissions";
		String filePath = "D:\\2dep\\git\\evsmc-base-service-2.4.1-hotfix\\src\\main\\java\\com\\bitnei\\cloud";
		getJavaWebFile(classNames, filePath);

		//2.
		printAnnotationValue(classNames, annotationName);
	}

	private static void printAnnotationValue(List<String> classNames, String annotationName) {
//		for (String className : classNames) {
//			try {
//				// 加载类
//				Class<?> clazz = Class.forName(className);
//				Api m = (Api) clazz.getAnnotation(Api.class);
//				String id = UtilHelper.getUUID();
//
//				String name = m.value();
//				String note = m.description();
//
//
//				log.error("INSERT INTO sys_module_data_group (id, name, note, create_time, create_by, update_time, " +
//								"update_by) VALUES ({}, {}, {}, '2023-07-27 14:24:00', 'renshuo', '2023-07-27 14:24:00', 'renshuo');",
//						id,name,note);
//				// 获取类的所有方法
//				Method[] methods = clazz.getDeclaredMethods();
//				for (Method method : methods) {
//					// 检查方法是否有目标注解
//					Class<? extends Annotation> aClass = (Class<? extends Annotation>) Class.forName(annotationName);
//					ApiOperation apiName = (ApiOperation) clazz.getAnnotation(ApiOperation.class);
//
//					String mName = apiName.value();
//					String mNote = apiName.notes();
//					String dataId = UtilHelper.getUUID();
//
//					RequiresPermissions annotation = (RequiresPermissions)method.getAnnotation(aClass);
//					if (annotation != null) {
//						String[] fieldName = annotation.value();
//						log.info("INSERT INTO sys_module_data_item (id, name, field_name, data_group_id, note, is_enable, create_time, create_by, update_time, update_by) " +
//										"VALUES ({}, {}, {}, {}, {}, '0', '2023-07-27 14:24:00', 'renshuo', '2023-07-27 14:24:00', 'renshuo');",
//								dataId,mName,fieldName,id,mNote);
//
//						String lkId = UtilHelper.getUUID();
//
//						log.info("INSERT INTO sys_module_data_lk (id, module_id, module_data_id) VALUES ({},{} , {});",lkId,dataId);
//
//					}
//				}
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			}
//		}
	}

	private static void getJavaWebFile(List<String> classNames, String filePath) {
		File file = new File(filePath);
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File file1 : files) {
				if (file1.isDirectory()) {
					File[] moduleFiles = file1.listFiles();
					for (File moduleFile : moduleFiles) {
						if (moduleFile.isDirectory()) {
							if ("web".equals(moduleFile.getName())) {
								File[] classFiles = moduleFile.listFiles();

								for (File classFile : classFiles) {
									if (classFile.isFile() && classFile.getName().endsWith("Controller.java")) {
										String packageName = "com.bitnei.cloud." + file1.getName() + ".web";
										String className = packageName + '.' + classFile.getName().substring(0, classFile.getName().length() - 5);
										classNames.add(className);
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
