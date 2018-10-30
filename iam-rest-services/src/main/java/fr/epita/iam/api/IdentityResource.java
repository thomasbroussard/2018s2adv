package fr.epita.iam.api;

import java.net.URI;
import java.net.URISyntaxException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.epita.iam.Identity;
import fr.epita.iam.business.services.IdentityManagementService;

@Path(IdentityResource.PATH)
public class IdentityResource {
	
	static final String PATH = "identities";

	@Inject
	IdentityManagementService ds;

	
	@POST
	@Path("/")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	public Response createIdentity(IdentityMessage message) throws URISyntaxException {
		Identity identity = toIdentity(message);
		ds.saveIdentity(identity);
		return Response.created(new URI(PATH + "/" + String.valueOf(identity.getId()))).build();
		
	}


	private Identity toIdentity(IdentityMessage message) {
		Identity identity = new Identity();
		identity.setBirthDate(message.getBirthDate());
		identity.setEmail(message.getEmail());
		identity.setFirstName(message.getFirstName());
		identity.setId(message.getId());
		identity.setLastName(message.getLastName());
		identity.setWorkAddress(message.getWorkAddress());
		return identity;
	}
	
}
