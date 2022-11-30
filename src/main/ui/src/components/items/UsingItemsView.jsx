import Tooltip from 'react-bootstrap/Tooltip';
import OverlayTrigger from "react-bootstrap/OverlayTrigger";
import NotDisposableItemDescription from "./tooltip/NotDisposableItemDescription";
import WearingItem from "./WearingItem"

function UsingItemsView ({wearingItems}) {
    const imagesPathPrefix = "/images/items/"
    const imagesPathSuffix = "_put_off"
    const imagesExtension = ".jpg"
    function pathImagesGenerator(itemType: String) {
        if(itemType in wearingItems) {
            return imagesPathPrefix+itemType+imagesExtension
        }
        return imagesPathPrefix+itemType+imagesPathSuffix+imagesExtension
    }
    return (
        <>
{/*            <img className ="rounded mx-auto d-block" src="/images/items/helmet.jpg" style={{wight: 50}, {height: 50}} alt="images" /> */}
           <div className="row">
               <div className="col-md-4 border">
                    <WearingItem isWearing={"sword" in wearingItems} itemType="sword" stats={wearingItems["sword"]} />
               </div>
{/*                <div className="col-md-4 border"> */}
{/*                    <img className ="rounded mx-auto d-block" src="/images/items/armor.jpg" style={{wight: 50}, {height: 50}} alt="images" /> */}
{/*                </div> */}
               <div className="col-md-4 border">
                   <WearingItem isWearing={"shield" in wearingItems} itemType="shield" stats={wearingItems["shield"]} />
               </div>
           </div>
{/*            <div className="row"> */}
{/*                <div className="col-md-4 border"> */}
{/*                    <img className ="rounded mx-auto d-block" src="/images/items/ring.jpg" style={{wight: 50}, {height: 50}} alt="images" /> */}
{/*                </div> */}
{/*                <div className="col-md-4 border"> */}
{/*                    <img className ="rounded mx-auto d-block" src="/images/items/trousers.jpg" style={{wight: 50}, {height: 50}} alt="images" /> */}
{/*                </div> */}
{/*                <div className="col-md-4 border"> */}
{/*                    <img className ="rounded mx-auto d-block" src="/images/items/ring.jpg" style={{wight: 50}, {height: 50}} alt="images" /> */}
{/*                </div> */}
{/*            </div> */}
{/*            <img className ="rounded mx-auto d-block" src="/images/items/boots.jpg" style={{wight: 50}, {height: 50}} alt="images" /> */}
        </>
    );

}

export default UsingItemsView