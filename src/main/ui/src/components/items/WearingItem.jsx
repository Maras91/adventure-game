import Tooltip from 'react-bootstrap/Tooltip';
import OverlayTrigger from "react-bootstrap/OverlayTrigger";
import NotDisposableItemDescription from "./tooltip/NotDisposableItemDescription";

function WearingItem ({ isWearing, itemType, stats}) {
    const imagesPathPrefix = "/images/items/"
    const imagesPathSuffix = "_put_off"
    const imagesExtension = ".jpg"
    function pathImagesGenerator(itemType: String) {
        if(isWearing) {
            return imagesPathPrefix+itemType+imagesExtension
        }
        return imagesPathPrefix+itemType+imagesPathSuffix+imagesExtension
    }

    return (<>
               { isWearing?
                   <OverlayTrigger placement="bottom" overlay={<Tooltip><NotDisposableItemDescription item = {stats} /></Tooltip>}>
                      <img className ="rounded mx-auto d-block" src={pathImagesGenerator(itemType)} style={{wight: 50}, {height: 50}} alt="images" />
                   </OverlayTrigger>
                   : <img className ="rounded mx-auto d-block" src={pathImagesGenerator(itemType)} style={{wight: 50}, {height: 50}} alt="images" />
               }
            </>
           )
}

export default WearingItem