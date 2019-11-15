package com.aem.training.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;
@Model(adaptables = Resource.class)
public class ImageGallery {
@Inject
@Optional
public Resource galleryItems;
}
