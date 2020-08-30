package hu.ptemik.gallery.servlets;

import hu.ptemik.gallery.control.Controller;
import hu.ptemik.gallery.entities.Picture;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class PictureServlet extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = System.getProperty("gallery.picturesFolder");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer pictureId = Integer.parseInt(request.getPathInfo().substring(1));
        Picture picture = Controller.findPicture(pictureId);
        File file = new File(UPLOAD_DIRECTORY + File.separator + picture.getUrl());
        String filename = file.getName();

        response.setHeader("Content-Type", getServletContext().getMimeType(filename));
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
        Files.copy(file.toPath(), response.getOutputStream());
    }

}
