package managers;

public class UserManager {

	private UserManager() {
		throw new IllegalStateException("UserManager class");
	}

	private static String username;
	private static String password;

	public static String getUsername(String arg_uname) {
		System.out.println(arg_uname + "Username");

		if (arg_uname.equalsIgnoreCase("SuperUser")) {
			username = getProperty("super.user.username");
		} else if (arg_uname.equalsIgnoreCase("AccountManager")) {
			username = getProperty("account.manager.username");
		} else if (arg_uname.equalsIgnoreCase("ProductManager")) {
			username = getProperty("product.manager.username");
		} else if (arg_uname.equalsIgnoreCase("ClientUser")) {
			username = getProperty("client.login.username");
		} else {
			username = getProperty("team.manager.username");
		}
		return username;
	}

	public static String getPassword(String arg_psswd) {
		System.out.println(arg_psswd + "Password");

		if (arg_psswd.equalsIgnoreCase("SuperUser")) {
			password = getProperty("super.user.password");
		} else if (arg_psswd.equalsIgnoreCase("AccountManager")) {
			password = getProperty("account.manager.password");
		} else if (arg_psswd.equalsIgnoreCase("ProductManager")) {
			password = getProperty("product.manager.password");
		} else if (arg_psswd.equalsIgnoreCase("ClientUser")) {
			password = getProperty("client.login.password");
		} else {
			password = getProperty("team.manager.username");
		}
		return password;
	}

	public static String getToken(String token) {
		System.out.println(token + " Token");

		if (token.equalsIgnoreCase("SuperUser")) {
			token = getProperty("super.user.token");
		} else if (token.equalsIgnoreCase("AccountManager")) {
			token = getProperty("account.manager.token");
		} else if (token.equalsIgnoreCase("ProductManager")) {
			token = getProperty("product.manager.token");
		} else if (token.equalsIgnoreCase("ClientUser")) {
			token = getProperty("client.login.token");
		} else {
			token = getProperty("team.manager.token");
		}
		return token;
	}

	public static String getkey(String key) {
		System.out.println(key + " Key");

		if (key.equalsIgnoreCase("SuperUser")) {
			key = getProperty("super.user.key");
		} else if (key.equalsIgnoreCase("AccountManager")) {
			key = getProperty("account.manager.key");
		} else if (key.equalsIgnoreCase("ProductManager")) {
			key = getProperty("product.manager.key");
		} else if (key.equalsIgnoreCase("ClientUser")) {
			key = getProperty("client.login.key");
		} else {
			key = getProperty("team.manager.key");
		}
		return key;
	}

	public static String getProperty(String key) {
		return ApplicationProperties.getInstance().getProperty(key);
	}
}
