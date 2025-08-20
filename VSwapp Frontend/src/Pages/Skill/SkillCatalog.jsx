import React,{useState,useEffect} from 'react'
import FilterBox from './FilterBox';
//import javaImage from '../../assets/Images/javaImage.jpg';
import axios from "axios";


export const SkillCatalog = () => {
  const [skills, setSkills] = useState([]);

  useEffect(() => {
    const fetchSkills = async () => {
      try {
        const res = await axios.get("http://localhost:8080/api/skill");
        setSkills(res.data);
      } catch (err) {
        console.error("Error fetching skills:", err);
      }
    };
    fetchSkills();
  }, []);

  return (
        <section className="py-16">
        <div className="bg-gradient-to-b from-[#090e2d] to-[#111827] min-h-screen text-white">
        <div className="text-3xl text-center text-white">
          <h4>Skills Catalog</h4>
          <hr className="w-[80px] border-t-2 border-blue-500 mx-auto mt-3" />
    </div>

    <div className="flex ml-24 mt-8 gap-8">
      {/* Filter Box */}
      <div className="bg-indigo-950 text-white p-8 rounded-3xl shadow-lg w-[350px] h-[650px] flex-shrink-0">
        <div className="flex flex-col">
          <span className="text-2xl">Filters</span>
          <hr className="w-[40px] border-t-2 border-blue-500 mt-2 mb-4" />
          <span className="text-lg mt-2">Categories</span>
        </div>
        <FilterBox />
      </div>

      {/* Skills Grid */}
      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-3 gap-8">
        {skills.map((skill) => (
          <div
            key={skill.id}
            className="bg-indigo-950 text-white rounded-3xl shadow-lg w-[320px] h-[250px] flex flex-col justify-between"
          >
            <img
              src={skill.imagePath}
              alt={skill.title}
              className="w-full h-[180px] rounded-3xl object-cover"
            />
            <div className="flex justify-between items-center px-4 py-2 ">
              <span className="text-base">{skill.category}</span>
              <span className="text-base cursor-pointer">Join</span>
            </div>
          </div>
        ))}
      </div>
    </div>
  </div>
</section>

);
}

export default SkillCatalog