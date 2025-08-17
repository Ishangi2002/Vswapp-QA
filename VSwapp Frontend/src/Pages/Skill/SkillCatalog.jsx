import React from 'react'
import FilterBox from './FilterBox';
import javaImage from '../../assets/Images/javaImage.jpg';


export const SkillCatalog = () => {
  return (
        <section className='py-16'>
        <div className="bg-gradient-to-b from-[#090e2d] to-[#111827] min-h-screen text-white">

            
 <div className="text-3xl text-center text-white">
        <h4>Skills Catalog</h4>
          <hr className="w-[80px]  border-t-2 border-blue-500 mx-auto mt-3 "></hr><br/>

      <div className="flex items-center min-h-screen ml-24">
          <div className="bg-indigo-950 text-white p-8 rounded-3xl shadow-lg w-[350px] h-[650px]">
            <div className="flex justify-start">
            <span className="text-2xl ">Filters</span>
            <hr className="w-[40px]  border-t-2 border-blue-500 ml-[-55px] mt-8 "></hr><br/>
            <span className="text-lg mt-12 ml-[-56px]">Catagories</span>

            </div>
            < FilterBox />
          </div>          
      </div>

      <div className="flex items-center justify-center ml-8 mt-[-690px]">
            <div className="bg-indigo-950 text-white rounded-3xl shadow-lg w-[320px] h-[230px]">
                <img
                    src={javaImage}
                    alt="Java Skill"
                    className="w-full h-[180px] rounded-3xl object-cover"
                />
                <div className="flex justify-between items-center px-4 mt-2 mb-96">
                    <span className="text-base">Java Script</span>
                    <span className="text-base">Join</span>
                </div>

            </div>

            
     </div>




</div>
</div>
        </section>
  )
}

export default SkillCatalog