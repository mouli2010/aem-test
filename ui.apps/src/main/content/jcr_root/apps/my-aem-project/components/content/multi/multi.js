
use(function() {
    var resourceResolver = resource.getResourceResolver();
    return {
      node : resourceResolver.getResource(currentNode.getPath() + "/products")
    };
});