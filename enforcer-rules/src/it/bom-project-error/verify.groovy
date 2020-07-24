def buildLog = new File(basedir, "build.log").text.replaceAll("\\r\\n", "\n")

assert buildLog.contains('''\
[ERROR] Linkage Checker rule found 4 errors. Linkage error report:
Class org.apache.avalon.framework.logger.Logger is not found;
  referenced by 1 class file
    org.apache.commons.logging.impl.AvalonLogger (commons-logging:commons-logging:1.1.1)
Class org.apache.log.Hierarchy is not found;
  referenced by 1 class file
    org.apache.commons.logging.impl.LogKitLogger (commons-logging:commons-logging:1.1.1)
Class org.apache.log.Logger is not found;
  referenced by 1 class file
    org.apache.commons.logging.impl.LogKitLogger (commons-logging:commons-logging:1.1.1)
(com.google.guava:guava:20.0) com.google.common.base.Verify's method verify(boolean, String, Object) is not found;
  referenced by 3 class files
    io.grpc.internal.ServiceConfigInterceptor (io.grpc:grpc-core:1.17.1)
    io.grpc.internal.JndiResourceResolverFactory (io.grpc:grpc-core:1.17.1)
    io.grpc.internal.DnsNameResolver (io.grpc:grpc-core:1.17.1)
''')

assert buildLog.contains('''[ERROR] Problematic artifacts in the dependency tree:
commons-logging:commons-logging:1.1.1 is at:
  com.google.api-client:google-api-client:1.27.0 (compile) / com.google.oauth-client:google-oauth-client:1.27.0 (compile) / com.google.http-client:google-http-client:1.27.0 (compile) / com.google.android:android:1.5_r4 (provided) / commons-logging:commons-logging:1.1.1 (compile)
  and 3 other dependency paths.
com.google.guava:guava:20.0 is at:
  com.google.api-client:google-api-client:1.27.0 (compile) / com.google.guava:guava:20.0 (compile)
  and 3 other dependency paths.
io.grpc:grpc-core:1.17.1 is at:
  io.grpc:grpc-core:1.17.1 (compile)
''');
