package com.s3a.poc.web.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** The Class HttpClientHandler. */
public class HttpClientHandler {

	private static final Logger logger = LoggerFactory.getLogger(HttpClientHandler.class);

	private String proxyIp;
	private Integer proxyPort;
	private String proxyUsername;
	private String proxyPassword;

	public HttpClientHandler() {
		super();
	}

	public HttpClientHandler(String proxyIp, Integer proxyPort, String proxyUsername, String proxyPassword) {
		super();
		this.proxyIp = proxyIp;
		this.proxyPort = proxyPort;
		this.proxyUsername = proxyUsername;
		this.proxyPassword = proxyPassword;
	}

	public String doPost(String url, String g_code, String secret) throws Exception {

		int timeout = 30;

		CloseableHttpResponse response = null;
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		nvps.add(new BasicNameValuePair("response", g_code));
		nvps.add(new BasicNameValuePair("secret", secret));

		// httpPost.setEntity(new UrlEncodedFormEntity(postParameters));

		if (proxyIp == null || proxyIp.length() == 0) {
			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			CloseableHttpClient httpclient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();

			// set the header and the content 
			HttpPost httpPost = new HttpPost(url);

			RequestConfig timeoutConfig = RequestConfig.custom().setSocketTimeout(timeout * 1000).setConnectTimeout(timeout * 1000).build();

			//httpPost.addHeader("Content-Type", "application/json");
			httpPost.setConfig(timeoutConfig);

			//httpPost.setEntity(new UrlEncodedFormEntity(nvps, recvEncoding));

			httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
			httpPost.setConfig(timeoutConfig);
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			System.out.println("url : " + url);
			System.out.println("g_code : " + g_code);
			response = httpclient.execute(httpPost);
		} else {

			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(new AuthScope(proxyIp, proxyPort), new UsernamePasswordCredentials(proxyUsername, proxyPassword));
			CloseableHttpClient httpclient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();

			HttpHost proxy = new HttpHost(proxyIp, proxyPort);
			RequestConfig config = RequestConfig.custom().setProxy(proxy).setSocketTimeout(timeout * 1000).setConnectTimeout(timeout * 1000).build();

			// set the header and the content 
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(config);
			httpPost.addHeader("Content-Type", "application/json");

			httpPost.setEntity(new UrlEncodedFormEntity(nvps));

			response = httpclient.execute(httpPost);
		}

		HttpEntity clientEntity = response.getEntity();
		BufferedReader reader = new BufferedReader(new InputStreamReader(clientEntity.getContent()));
		String lines;
		StringBuilder sb = new StringBuilder();
		while ((lines = reader.readLine()) != null) {
			sb.append(lines);
		}
		HttpClientUtils.closeQuietly(response);
		logger.info(sb.toString());
		return sb.toString();
	}

}
