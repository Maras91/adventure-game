package com.app.webcalculator.model.resources

import com.app.webcalculator.model.resources.Resources
import com.app.webcalculator.model.resources.ResourcesJsonObject
import org.springframework.stereotype.Service

@Service
class ResourcesJsonConverter {
    fun converterResourcesToJson (resources : Resources) : ResourcesJsonObject {
        return ResourcesJsonObject(resources.getGold(),resources.getIron(),resources.getMeat())
    }
}