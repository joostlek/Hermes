package nl.jtosti.projects.hermes.servlets;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        BufferedReader bufferedReader = new BufferedReader(req.getReader());
        Gson gson = new Gson();
        LoginRequest loginRequest = gson.fromJson(bufferedReader.readLine(), LoginRequest.class);
        if (loginRequest.getPassword().equals("test")) {
            resp.addCookie(new Cookie("session", req.getSession().getId()));
            resp.setStatus(200);
            resp.getOutputStream().println("{\"id\":1,\"firstName\":\"Joost\",\"middleName\":\"\",\"lastName\":\"Lekkerkerker\", \"email\":\"\", \"roles\":\"\", \"locations\":[], \"promotions\":[], \"images\":[], \"phoneNumber\":\"\", \"street\":\"\", \"houseNumber\":\"\", \"zipCode\":\"\", \"city\":\"\", \"country\":\"\"}");
        } else {
            resp.setStatus(404);
        }
    }

    class LoginRequest {
        private String email;
        private String password;

        public LoginRequest() {
        }

        public LoginRequest(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        protected String getPassword() {
            return password;
        }
    }
}
