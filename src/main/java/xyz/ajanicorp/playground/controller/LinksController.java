package xyz.ajanicorp.playground.controller;

import com.google.common.base.Throwables;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import xyz.ajanicorp.playground.configuration.HttpHeadersProvider;
import xyz.ajanicorp.playground.constants.PlaygroundConstants;
import xyz.ajanicorp.playground.constants.PlaygroundStatusCodes;
import xyz.ajanicorp.playground.core.PlaygroundException;
import xyz.ajanicorp.playground.core.PlaygroundMessage;
import xyz.ajanicorp.playground.core.PlaygroundResult;
import xyz.ajanicorp.playground.domain.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import xyz.ajanicorp.playground.service.LinkCrudService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ajani on 17/10/2017.
 */
@CrossOrigin(origins = "*")
@RestController
public class LinksController {

    @Autowired
    LinkCrudService linkCrudService;

    @Autowired
    Gson gson;

    public static final Logger logger = LoggerFactory.getLogger(LinksController.class);




    /**
     * @api {get} /Links/ Request all links
     * @apiName GetLinks
     * @apiGroup Links
     *
     *
     * @apiSuccess {Object[]} All links.
     */
    @GetMapping(PlaygroundConstants.LINKS_BASE_URL)
    @ResponseBody
    public ResponseEntity<String> findAllLinks(){
        List<Link> links = new ArrayList<>();
        try{
            links = linkCrudService.findAll();
        }catch(Exception e){
            logger.error(Throwables.getStackTraceAsString(e));
            return new ResponseEntity<String>(gson
                    .toJson(new PlaygroundResult<String>(PlaygroundStatusCodes.DEADBEEF,
                            PlaygroundConstants.DEADBEEF_TEXT))
                    ,HttpHeadersProvider.GetJsonHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(gson.toJson(
                new PlaygroundResult<List<Link>>(PlaygroundStatusCodes.SUP,links)
        ),HttpHeadersProvider.GetJsonHeaders(),HttpStatus.OK);
    }


    @PostMapping(PlaygroundConstants.LINKS_BASE_URL)
    @ResponseBody
    public ResponseEntity<String> addLink(@RequestBody String jsonRequest){
        try{
            Link link = gson.fromJson(jsonRequest,Link.class);

            if(linkCrudService.findByCode(link.getCode()) != null){
                return new ResponseEntity<String>(gson.toJson(
                        new PlaygroundResult<String>(PlaygroundStatusCodes.ALREADY_EXISTS,"Link already exists"))
                        ,HttpHeadersProvider.GetJsonHeaders(),HttpStatus.CONFLICT);
            }

            Link insertedLink = linkCrudService.save(link);

            return new ResponseEntity<String>(gson.toJson(
                    new PlaygroundResult<Link>(PlaygroundStatusCodes.SUP,insertedLink)),
                    HttpHeadersProvider.GetJsonHeaders(),HttpStatus.CREATED);
        }catch (Exception e){
            logger.error(Throwables.getStackTraceAsString(e));
            return new ResponseEntity<String>(gson
                    .toJson(new PlaygroundResult<String>(PlaygroundStatusCodes.DEADBEEF,
                            PlaygroundConstants.DEADBEEF_TEXT))
                    ,HttpHeadersProvider.GetJsonHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(PlaygroundConstants.LINKS_BASE_URL + "/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteLink(@PathVariable int id){
        try{
            linkCrudService.delete(id);
            return new ResponseEntity<String>(gson.toJson(
                    new PlaygroundResult<Link>(PlaygroundStatusCodes.SUP)),
                    HttpHeadersProvider.GetJsonHeaders(),HttpStatus.NO_CONTENT);
        }catch (Exception e){
            logger.error(Throwables.getStackTraceAsString(e));
            return new ResponseEntity<String>(gson
                    .toJson(new PlaygroundResult<String>(PlaygroundStatusCodes.DEADBEEF,
                            PlaygroundConstants.DEADBEEF_TEXT))
                    ,HttpHeadersProvider.GetJsonHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(PlaygroundConstants.LINKS_BASE_URL + "/{id}")
    @ResponseBody
    public ResponseEntity<String> getLink(@PathVariable int id){
        List<Link> links = new ArrayList<>();
        try{
            Link link = linkCrudService.find(id);

            if(link!=null)
                links.add(link);

            return new ResponseEntity<String>(gson.toJson(
                new PlaygroundResult<List<Link>>(PlaygroundStatusCodes.SUP,links)),
                HttpHeadersProvider.GetJsonHeaders(),HttpStatus.OK);

        }catch (Exception e){
            logger.error(Throwables.getStackTraceAsString(e));
            return new ResponseEntity<String>(gson
                    .toJson(new PlaygroundResult<String>(PlaygroundStatusCodes.DEADBEEF,
                            PlaygroundConstants.DEADBEEF_TEXT))
                    ,HttpHeadersProvider.GetJsonHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(PlaygroundConstants.LINKS_BASE_URL)
    @ResponseBody
    public ResponseEntity<String> updateLink(@RequestBody String jsonRequest){
        List<Link> links = new ArrayList<>();
        try{
            Link link = gson.fromJson(jsonRequest,Link.class);

            PlaygroundResult<Link> result = linkCrudService.update(link);

            switch(result.getStatusCode()){
                case SUP:
                    if(result.getResult() != null)
                        links.add(result.getResult());

                    return new ResponseEntity<String>(gson.toJson(
                            new PlaygroundResult<List<Link>>(PlaygroundStatusCodes.SUP,links)
                    ),HttpHeadersProvider.GetJsonHeaders(),HttpStatus.OK);
                case NOT_EXISTS:
                    return new ResponseEntity<String>(gson.toJson(
                            new PlaygroundResult<String>(PlaygroundStatusCodes.NOT_EXISTS,"Link not exists"))
                            ,HttpHeadersProvider.GetJsonHeaders(),HttpStatus.CONFLICT);
                default:
                    return new ResponseEntity<String>(gson.toJson(
                            new PlaygroundResult<String>(PlaygroundStatusCodes.UNKNOWN,"Unknown error"))
                            ,HttpHeadersProvider.GetJsonHeaders(),HttpStatus.CONFLICT);
            }


        }catch(PlaygroundException e){
            logger.error(Throwables.getStackTraceAsString(e));
            return new ResponseEntity<String>(gson
                    .toJson(new PlaygroundResult<List<PlaygroundMessage>>(PlaygroundStatusCodes.ARGUMENT_VALIDATION_ERROR,
                            e.getPlaygroundMessageList()))
                    ,HttpHeadersProvider.GetJsonHeaders(),HttpStatus.BAD_REQUEST);
        }

        catch(Exception e){
            logger.error(Throwables.getStackTraceAsString(e));
            return new ResponseEntity<String>(gson
                    .toJson(new PlaygroundResult<String>(PlaygroundStatusCodes.DEADBEEF,
                            PlaygroundConstants.DEADBEEF_TEXT))
                    ,HttpHeadersProvider.GetJsonHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
