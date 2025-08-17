import React, { useState } from 'react';

const categoryOptions = [
  { id: 'lang', label: 'Languages', icon: '🔠' },
  { id: 'programming', label: 'Programming', icon: '💻' },
  { id: 'sports', label: 'Sports', icon: '🏏' },
  { id: 'music', label: 'Music', icon: '🎵' },
  { id: 'art', label: 'Art', icon: '🎨' },
  { id: 'drawing', label: 'Drawing', icon: '📐' },
];

const levelOptions = [
  { id: 'beginner', label: 'Beginner' },
  { id: 'intermediate', label: 'Intermediate' },
  { id: 'advanced', label: 'Advanced' },
];

const FilterBox = () => {
  const [checkedCategories, setCheckedCategories] = useState({});
  const [checkedLevels, setCheckedLevels] = useState({});

  const handleCategoryChange = (id) => {
    setCheckedCategories((prev) => ({
      ...prev,
      [id]: !prev[id],
    }));
    console.log(`Category filter - ${id}:`, !checkedCategories[id]);
  };

  const handleLevelChange = (id) => {
    setCheckedLevels((prev) => ({
      ...prev,
      [id]: !prev[id],
    }));
    console.log(`Level filter - ${id}:`, !checkedLevels[id]);
  };

  return (
    <div className="mt-6 ml-2 space-y-4">
      {/* Category Filters */}
      {categoryOptions.map((option) => (
        <div key={option.id} className="flex items-center">
          <input
            type="checkbox"
            id={option.id}
            checked={checkedCategories[option.id] || false}
            onChange={() => handleCategoryChange(option.id)}
            className="w-4 h-4 text-blue-500 bg-gray-100 border-gray-300 rounded focus:ring-blue-500"
          />
          <label htmlFor={option.id} className="ml-2 text-lg font-medium text-white">
            <div className="flex items-center gap-3">
              <span>{option.icon}</span>
              <span>{option.label}</span>
            </div>
          </label>
        </div>
      ))}

      {/* Levels Title */}
      <div className="pt-6 flex justify-start ml-[-8px] ">
        <span className="text-lg">Levels</span>
      </div>

      {/* Level Filters */}
      {levelOptions.map((level) => (
        <div key={level.id} className="flex items-center">
          <input
            type="checkbox"
            id={level.id}
            checked={checkedLevels[level.id] || false}
            onChange={() => handleLevelChange(level.id)}
            className="w-4 h-4 text-blue-500 bg-gray-100 border-gray-300 rounded focus:ring-blue-500"
          />
          <label htmlFor={level.id} className="ml-2 text-lg font-medium text-white">
            {level.label}
          </label>
        </div>
      ))}
     <hr className="w-[200px] border-t border-blue-500 mt-8 mb-[-1000px] ml-[-12px]" />
      <div className="pt-[-200px] flex justify-start ml-[-10px] ">
        <span className="text-sm">Found: 12 Skills</span>
      </div>
    </div>
  );
};

export default FilterBox;
