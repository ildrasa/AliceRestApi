package com.cgir;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cgir.Alice;

@Path("/alice")
public class JsonService {

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Alice getAliceInJSON() {

		Alice alice = new Alice();

		return alice;
	}

	@GET
	@Path("/name")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAliceNameInJSON() {

		Alice alice = new Alice();

		return alice.getName();
	}

	@GET
	@Path("/version")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAliceVersionInJSON() {

		Alice alice = new Alice();

		return alice.getVersion();
	}
}
