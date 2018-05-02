package com.cristici.tpt;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.stream.Stream;

import static java.util.Optional.ofNullable;
import static java.util.stream.Stream.empty;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException, MalformedURLException, InvocationTargetException, IllegalAccessException {
        ClassPathSupport classPathSupport = new ClassPathSupport();
        URLClassLoader springClassLoader = new URLClassLoader(classPathSupport.getApplicationClasspath(), null);
        Class<?> runner = springClassLoader.loadClass("com.cristici.tpt.Runner");
        final Method method = runner.getMethod("run", ClassLoader.class);
        method.invoke(null, springClassLoader);
    }


    private static class ClassPathSupport {

        URL[] getApplicationClasspath() throws MalformedURLException {
            URL[] libsClassPath = getUrls(new File(System.getProperty("user.dir") + "/out/lib/"));
            URL[] classpath = new URL[libsClassPath.length + 1];
            classpath[0] = getUrl("/out/production/classes/");
            for( int i = 0; i < libsClassPath.length; i++) {
                classpath[i+1] = libsClassPath[i];
            }
            return classpath;
        }

        private URL getUrl(String subDir) throws MalformedURLException {
            return new File(System.getProperty("user.dir") + subDir).toURI().toURL();
        }

        private URL[] getUrls(File location) {
            return ofNullable(location)
                    .map(Stream::of)
                    .orElse(empty())
                    .filter(File::isDirectory)
                    .flatMap(this::listFiles)
                    .filter(this::isDirectoryOrJar)
                    .map(this::toURL)
                    .toArray(URL[]::new);
        }

        private Stream<File> listFiles(File location) {
            return Stream.of(location.listFiles());
        }

        private boolean isDirectoryOrJar(File file) {
            return file.isFile() ? file.getName().endsWith(".jar") : file.isDirectory();
        }

        private URL toURL(File file) {
            try {
                return file.toURI().toURL();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}